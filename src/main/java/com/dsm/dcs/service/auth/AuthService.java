package com.dsm.dcs.service.auth;

import com.dsm.dcs.dto.TokenDto;
import com.dsm.dcs.dto.request.LoginRequest;
import com.dsm.dcs.dto.request.UserSignUpRequest;
import com.dsm.dcs.dto.response.RoleResponse;
import com.dsm.dcs.entity.Role;
import com.dsm.dcs.entity.account.Account;
import com.dsm.dcs.entity.account.AccountRepository;
import com.dsm.dcs.entity.deviceToken.DeviceToken;
import com.dsm.dcs.entity.deviceToken.DeviceTokenRepository;
import com.dsm.dcs.entity.auth.RefreshToken;
import com.dsm.dcs.entity.auth.RefreshTokenRepository;
import com.dsm.dcs.exception.PasswordMismatchException;
import com.dsm.dcs.exception.RefreshTokenNotFoundException;
import com.dsm.dcs.facade.UserFacade;
import com.dsm.dcs.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {

    private final DeviceTokenRepository deviceTokenRepository;
    private final AccountRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserFacade userFacade;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public TokenDto signIn(LoginRequest request) {
        Account account = userFacade.getUserByAccountId(request.getAccountId());

        if (!passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        deviceTokenRepository.save(DeviceToken.builder()
                .accountId(account.getAccountId())
                .deviceToken(request.getDeviceToken()).build());

        return TokenDto.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(account.getAccountId(), Role.ROLE_USER))
                .refreshToken(jwtTokenProvider.generateRefreshToken(account.getAccountId(), Role.ROLE_USER))
                .build();
    }

    public TokenDto signUp(UserSignUpRequest request) {

        userFacade.checkUserExists(request.getAccountId());
        userFacade.checkPhoneNumberExists(request.getPhoneNumber());

        Account user = userRepository.save(Account.builder()
                .accountId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .role(Role.ROLE_USER)
                .phoneNumber(request.getPhoneNumber())
                .build());

        deviceTokenRepository.save(DeviceToken.builder()
                .accountId(user.getAccountId())
                .deviceToken(request.getDeviceToken()).build());

        return TokenDto.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(user.getAccountId(), user.getRole()))
                .refreshToken(jwtTokenProvider.generateRefreshToken(user.getAccountId(), user.getRole()))
                .build();
    }

    public void logout() {
        Account account = userFacade.getCurrentUser();
        RefreshToken refreshToken = refreshTokenRepository.findById(account.getAccountId())
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);
        refreshTokenRepository.delete(refreshToken);
    }

    public RoleResponse getAccountRole() {
        return new RoleResponse(userFacade.getCurrentUser().getRole().name());
    }

}
