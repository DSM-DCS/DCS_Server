package com.dsm.dcs.dto.response;

import com.dsm.dcs.entity.Authority;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@Builder
public class UserTokenResponse {

    private final String accessToken;
    private final String refreshToken;
    private final Authority authority;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final ZonedDateTime expiredAt;
}
