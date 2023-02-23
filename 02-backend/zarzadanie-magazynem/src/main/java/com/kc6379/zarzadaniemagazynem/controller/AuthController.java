package com.kc6379.zarzadaniemagazynem.controller;

import com.kc6379.zarzadaniemagazynem.dto.AuthenticationRequest;
import com.kc6379.zarzadaniemagazynem.dto.RefreshTokenRequest;
import com.kc6379.zarzadaniemagazynem.dto.RegisterRequest;
import com.kc6379.zarzadaniemagazynem.security.AuthenticationResponse;
import com.kc6379.zarzadaniemagazynem.service.AuthenticationService;
import com.kc6379.zarzadaniemagazynem.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signup(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
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
