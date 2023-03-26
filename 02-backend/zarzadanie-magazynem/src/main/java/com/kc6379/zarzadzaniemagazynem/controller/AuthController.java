package com.kc6379.zarzadzaniemagazynem.controller;

import com.kc6379.zarzadzaniemagazynem.dto.AuthenticationRequest;
import com.kc6379.zarzadzaniemagazynem.dto.CompleteRegistrationRequest;
import com.kc6379.zarzadzaniemagazynem.dto.RefreshTokenRequest;
import com.kc6379.zarzadzaniemagazynem.dto.RegisterRequest;
import com.kc6379.zarzadzaniemagazynem.security.AuthenticationResponse;
import com.kc6379.zarzadzaniemagazynem.service.AuthenticationService;
import com.kc6379.zarzadzaniemagazynem.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Collections;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest request) {
        authenticationService.signup(request);
        return ResponseEntity.ok("Wysłano email aktywacyjny");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/complete-registration")
    public ResponseEntity<?> completeRegistration(@RequestBody CompleteRegistrationRequest request) {
        authenticationService.completeRegistration(request);
        return ResponseEntity.ok(Collections.singletonMap("message", "Pomyślnie dokończono rejestrację"));
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(
            @PathVariable String token
    ) {
        authenticationService.verifyAccount(token);
        return ResponseEntity.ok("Konto zostało pomyślnie zweryfikowane");
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authenticationService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Usuwanie tokenu odświeżającego powiodło się!");
    }

}
