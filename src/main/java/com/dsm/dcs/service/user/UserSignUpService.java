package com.dsm.dcs.service.user;

import com.dsm.dcs.dto.request.UserSignUpRequest;
import com.dsm.dcs.dto.TokenDto;
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

@Transactional
@RequiredArgsConstructor
@Service
public class UserSignUpService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserFacade userFacade;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public TokenDto execute(UserSignUpRequest request) {

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

        return TokenDto.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(user.getAccountId()))
                .refreshToken(jwtTokenProvider.generateRefreshToken(user.getAccountId()))
                .build();
    }
}
