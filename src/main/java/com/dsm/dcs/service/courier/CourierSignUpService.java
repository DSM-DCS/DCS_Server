package com.dsm.dcs.service.courier;

import com.dsm.dcs.dto.request.CourierSignUpRequest;
import com.dsm.dcs.dto.response.UserTokenResponse;
import com.dsm.dcs.entity.Authority;
import com.dsm.dcs.entity.courier.Courier;
import com.dsm.dcs.entity.courier.CourierRepository;
import com.dsm.dcs.facade.CourierFacade;
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
public class CourierSignUpService {

    private final JwtProperties jwtProperties;
    private final JwtTokenProvider jwtTokenProvider;
    private final CourierFacade courierFacade;
    private final CourierRepository courierRepository;
    private final PasswordEncoder passwordEncoder;

    public UserTokenResponse execute(CourierSignUpRequest request) {

        courierFacade.checkUserExists(request.getAccountId());

        Courier courier = courierRepository.save(Courier.builder()
                .accountId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .authority(Authority.COURIER)
                .build());

        String accessToken = jwtTokenProvider.generateAccessToken(courier.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(courier.getAccountId());

        return UserTokenResponse.builder()
                .accessToken(accessToken)
                .authority(courier.getAuthority())
                .expiredAt(ZonedDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(refreshToken)
                .build();
    }
}
