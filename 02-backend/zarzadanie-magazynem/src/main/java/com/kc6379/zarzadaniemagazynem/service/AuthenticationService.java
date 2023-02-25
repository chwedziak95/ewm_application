package com.kc6379.zarzadaniemagazynem.service;

import com.kc6379.zarzadaniemagazynem.dto.AuthenticationRequest;
import com.kc6379.zarzadaniemagazynem.dto.RefreshTokenRequest;
import com.kc6379.zarzadaniemagazynem.dto.RegisterRequest;
import com.kc6379.zarzadaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadaniemagazynem.model.NotificationEmail;
import com.kc6379.zarzadaniemagazynem.model.User;
import com.kc6379.zarzadaniemagazynem.model.VerificationToken;
import com.kc6379.zarzadaniemagazynem.repository.RefreshTokenRepository;
import com.kc6379.zarzadaniemagazynem.repository.UserRepository;
import com.kc6379.zarzadaniemagazynem.repository.VerificationTokenRepository;
import com.kc6379.zarzadaniemagazynem.security.AuthenticationResponse;
import com.kc6379.zarzadaniemagazynem.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final VerificationTokenRepository verificationTokenRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenService refreshTokenService;


    public AuthenticationResponse signup(RegisterRequest request) {

        var userEmailExist = userRepository.findByEmail(request.getEmail());
        if (userEmailExist.isPresent()) {
            throw new EwmAppException("Email jest już zajęty");
        }
        var UserEmailNameSurname = userRepository.findByEmailAndLastNameAndFirstName(request.getEmail(), request.getLastName(), request.getFirstName());
        if (UserEmailNameSurname.isPresent()) {
            throw new EwmAppException("Użytkownik o podanych danych już istnieje");
        }

        var user = User.builder()
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .created(Instant.now())
                .enabled(false)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);


        var token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Proszę potwierdzić swoje konto",
                user.getEmail(), "Potwierdzamy rejestrację w Aplikacji wspomagającej zarządzanie magazynem, " +
                "Kliknij w poniższy link aby aktywować swoje konto : " +
                "http://localhost:8080/api/v1/auth/accountVerification/" + token));

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtService.getJwtExpirationInMillis()))
                .username(user.getUsername())
                .build();
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtService.getJwtExpirationInMillis()))
                .username(user.getUsername())
                .build();
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono użytkownika o adresie email: " + email));
    }


    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        var user = userRepository.findByEmail(refreshTokenRequest.getUsername())
                .orElseThrow();
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtService.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new EwmAppException("Niepoprawny token")));
    }

    @Transactional
    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String email = verificationToken.getUser().getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EwmAppException("Nie znaleziono użytkownika o adresie email: " + email));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

}