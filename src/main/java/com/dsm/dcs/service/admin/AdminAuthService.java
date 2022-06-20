package com.dsm.dcs.service.admin;

import com.dsm.dcs.dto.TokenDto;
import com.dsm.dcs.dto.request.LoginRequest;
import com.dsm.dcs.entity.admin.Admin;
import com.dsm.dcs.exception.PasswordMismatchException;
import com.dsm.dcs.facade.AdminFacade;
import com.dsm.dcs.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class AdminAuthService {

    private final PasswordEncoder passwordEncoder;
    private final AdminFacade adminFacade;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenDto adminSignIn(LoginRequest request) {
        Admin admin = adminFacade.findByAdminId(request.getAccountId());

        if(!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        return TokenDto.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(admin.getAdminId(), admin.getRole()))
                .refreshToken(jwtTokenProvider.generateRefreshToken(admin.getAdminId(), admin.getRole()))
                .build();
    }
}
