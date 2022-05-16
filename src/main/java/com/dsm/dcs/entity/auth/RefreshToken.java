package com.dsm.dcs.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@RedisHash
public class RefreshToken implements Serializable {

    @Id
    private String accountId;

    @Indexed
    private String token;

    @TimeToLive
    private Long refreshExp;

    public void updateToken(String token, Long refreshExp) {

        this.token = token;
        this.refreshExp = refreshExp;
    }

}
