package com.dsm.dcs.entity.user;

import com.dsm.dcs.entity.Action;
import lombok.AccessLevel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class UserAuthCode {

    @Id
    private String email;

    private String code;

    private Action action;

    @TimeToLive
    private Long timeToLive;

    private boolean isVerify;

    @Builder
    public UserAuthCode(String email, String code, Action action) {
        this.email = email;
        this.code = code;
        this.action = action;
        this.timeToLive = 180000L;
        this.isVerify = false;
    }

    public void updateAuthCode(String code) {
        this.code = code;
        this.timeToLive = 180L;
    }

    public UserAuthCode verify() {
        this.isVerify = true;
        return this;
    }
}
