package com.dsm.dcs.service.teacher;

import com.dsm.dcs.dto.request.TeacherSignUpRequest;
import com.dsm.dcs.dto.response.UserTokenResponse;
import com.dsm.dcs.entity.Authority;
import com.dsm.dcs.entity.teacher.Teacher;
import com.dsm.dcs.entity.teacher.TeacherRepository;
import com.dsm.dcs.facade.TeacherFacade;
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
public class TeacherSignUpService {

    private final JwtProperties jwtProperties;
    private final JwtTokenProvider jwtTokenProvider;
    private final TeacherFacade teacherFacade;
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    public UserTokenResponse execute(TeacherSignUpRequest request) {

        teacherFacade.checkUserExists(request.getAccountId());

        Teacher teacher = teacherRepository.save(Teacher.builder()
                .accountId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .authority(Authority.TEACHER)
                .build());

        String accessToken = jwtTokenProvider.generateAccessToken(teacher.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(teacher.getAccountId());

        return UserTokenResponse.builder()
                .accessToken(accessToken)
                .authority(teacher.getAuthority())
                .expiredAt(ZonedDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(refreshToken)
                .build();
    }
}
