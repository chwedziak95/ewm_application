package com.kc6379.zarzadzaniemagazynem.service;

import com.kc6379.zarzadzaniemagazynem.dto.AuthenticationRequest;
import com.kc6379.zarzadzaniemagazynem.dto.CompleteRegistrationRequest;
import com.kc6379.zarzadzaniemagazynem.dto.RefreshTokenRequest;
import com.kc6379.zarzadzaniemagazynem.dto.RegisterRequest;
import com.kc6379.zarzadzaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadzaniemagazynem.model.RegistrationEmail;
import com.kc6379.zarzadzaniemagazynem.model.User;
import com.kc6379.zarzadzaniemagazynem.model.VerificationToken;
import com.kc6379.zarzadzaniemagazynem.repository.UserRepository;
import com.kc6379.zarzadzaniemagazynem.repository.VerificationTokenRepository;
import com.kc6379.zarzadzaniemagazynem.security.AuthenticationResponse;
import com.kc6379.zarzadzaniemagazynem.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
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
    private final RefreshTokenService refreshTokenService;


    public void signup(RegisterRequest request) {

        var userEmailExist = userRepository.findByEmail(request.getEmail());
        if (userEmailExist.isPresent()) {
            throw new EwmAppException("Email jest już zajęty");
        }

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .created(Instant.now())
                .enabled(false)
                .build();
        userRepository.save(user);

        var token = generateVerificationToken(user);
        mailService.sendMail(new RegistrationEmail("Dokończ rejestrację",
                user.getEmail(), "https://localhost:4200/complete-registration?token=" + token,
                user.getFirstName(), user.getLastName()));


    }

    @Transactional
    public String completeRegistration(CompleteRegistrationRequest request) {
        var verificationToken = verificationTokenRepository.findByToken(request.getToken())
                .orElseThrow(() -> new EwmAppException("Invalid registration token"));
        var user = verificationToken.getUser();

        if (request.getPassword().equals(request.getConfirmPassword())) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setEnabled(true);
            userRepository.save(user);
            // Registration links are single-use; otherwise the link could reset the password later.
            verificationTokenRepository.delete(verificationToken);

            return "Registration completed successfully";
        } else {
            throw new EwmAppException("Passwords do not match");
        }
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
                .orElseThrow(() -> new EwmAppException("Nieprawidłowe dane logowania"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshTokenService.generateRefreshToken(user).getToken())
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
        // Issue the JWT for the token's owner, never for the username the client sends.
        User user = refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtService.getJwtExpirationInMillis()))
                .username(user.getUsername())
                .build();
    }

}