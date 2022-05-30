package com.dsm.dcs.service.user;

import com.dsm.dcs.dto.request.UserSignUpRequest;
import com.dsm.dcs.dto.response.UserTokenResponse;
import com.dsm.dcs.entity.Authority;
import com.dsm.dcs.entity.user.User;
import com.dsm.dcs.entity.user.UserRepository;
import com.dsm.dcs.facade.UserFacade;
import com.dsm.dcs.security.jwt.JwtProperties;
import com.dsm.dcs.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Transactional
@RequiredArgsConstructor
@Service
public class UserSignUpService {

    private final JwtProperties jwtProperties;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserFacade userFacade;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserTokenResponse execute(UserSignUpRequest request) {

        userFacade.checkUserExists(request.getAccountId());
        userFacade.checkEmailExists(request.getEmail());
        userFacade.checkStudentNumberExists(request.getStudentNumber());
        userFacade.checkPhoneNumberExists(request.getPhoneNumber());

        User user = userRepository.save(User.builder()
                .accountId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .email(request.getEmail())
                .authority(Authority.USER)
                .studentNumber(request.getStudentNumber())
                .phoneNumber(request.getPhoneNumber())
                .build());

        String accessToken = jwtTokenProvider.generateAccessToken(user.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getAccountId());

        return UserTokenResponse.builder()
                .accessToken(accessToken)
                .authority(user.getAuthority())
                .expiredAt(ZonedDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(refreshToken)
                .build();
    }
}
