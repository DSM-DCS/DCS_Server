package com.dsm.dcs.service.user;

import com.dsm.dcs.dto.TokenDto;
import com.dsm.dcs.dto.request.LoginRequest;
import com.dsm.dcs.dto.request.UserSignUpRequest;
import com.dsm.dcs.entity.Role;
import com.dsm.dcs.entity.deviceToken.DeviceToken;
import com.dsm.dcs.entity.deviceToken.DeviceTokenRepository;
import com.dsm.dcs.entity.user.User;
import com.dsm.dcs.entity.user.UserRepository;
import com.dsm.dcs.exception.PasswordMismatchException;
import com.dsm.dcs.facade.UserFacade;
import com.dsm.dcs.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserAuthService {

    private final DeviceTokenRepository deviceTokenRepository;
    private final UserRepository userRepository;
    private final UserFacade userFacade;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public TokenDto signIn(LoginRequest request) {
        User user = userFacade.getUserByAccountId(request.getAccountId());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }
        
        deviceTokenRepository.save(DeviceToken.builder()
                .accountId(user.getAccountId())
                .deviceToken(request.getDeviceToken()).build());

        return TokenDto.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(user.getAccountId(), Role.ROLE_USER))
                .refreshToken(jwtTokenProvider.generateRefreshToken(user.getAccountId(), Role.ROLE_USER))
                .build();
    }

    public TokenDto signUp(UserSignUpRequest request) {

        userFacade.checkUserExists(request.getAccountId());
        userFacade.checkEmailExists(request.getEmail());
        userFacade.checkStudentNumberExists(request.getStudentNumber());
        userFacade.checkPhoneNumberExists(request.getPhoneNumber());

        User user = userRepository.save(User.builder()
                .accountId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .email(request.getEmail())
                .role(Role.ROLE_USER)
                .studentNumber(request.getStudentNumber())
                .phoneNumber(request.getPhoneNumber())
                .build());

        return TokenDto.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(user.getAccountId(), user.getRole()))
                .refreshToken(jwtTokenProvider.generateRefreshToken(user.getAccountId(), user.getRole()))
                .build();
    }

}
