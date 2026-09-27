package com.kc6379.zarzadzaniemagazynem.service;

import com.kc6379.zarzadzaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadzaniemagazynem.model.RefreshToken;
import com.kc6379.zarzadzaniemagazynem.model.User;
import com.kc6379.zarzadzaniemagazynem.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken(User user){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());
        refreshToken.setUser(user);

        return refreshTokenRepository.save(refreshToken);
    }

    // Returns the user the token was issued to; tokens without an owner are rejected.
    User validateRefreshToken(String token){
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new EwmAppException("Nieprawidłowy token odświeżania"));
        if (refreshToken.getUser() == null) {
            throw new EwmAppException("Nieprawidłowy token odświeżania");
        }
        return refreshToken.getUser();
    }

    public void deleteRefreshToken(String token){
        refreshTokenRepository.deleteByToken(token);
    }
}
