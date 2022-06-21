package com.dsm.dcs.service.auth;

import com.dsm.dcs.dto.TokenDto;
import com.dsm.dcs.entity.auth.RefreshToken;
import com.dsm.dcs.entity.auth.RefreshTokenRepository;
import com.dsm.dcs.exception.EffectiveJwtException;
import com.dsm.dcs.exception.InvalidJwtException;
import com.dsm.dcs.exception.NotRefreshTokenException;
import com.dsm.dcs.exception.RefreshTokenNotFoundException;
import com.dsm.dcs.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@RequiredArgsConstructor
@Service
public class TokenRefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenDto execute(@Valid TokenDto jwtToken) {

        if(jwtTokenProvider.validateToken(jwtToken.getAccessToken())) {
            throw EffectiveJwtException.EXCEPTION;
        }

        if (!jwtTokenProvider.isRefreshToken(jwtToken.getRefreshToken())) {
            throw NotRefreshTokenException.EXCEPTION;
        }

        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(jwtToken.getRefreshToken())
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        TokenDto token = TokenDto.builder()
                .refreshToken(refreshToken.getRefreshToken())
                .accessToken(jwtTokenProvider.generateAccessToken(refreshToken.getAccountId(), refreshToken.getRole()))
                .build();

        RefreshToken newRefreshToken = refreshToken.updateToken(token.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        return token;

    }

}
