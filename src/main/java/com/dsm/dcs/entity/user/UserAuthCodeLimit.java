package com.dsm.dcs.entity.user;

import com.dsm.dcs.entity.Action;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class UserAuthCodeLimit {

    @Id
    private String email;

    private Action action;

    private Long ttl;

    private int count;

    @Builder
    public UserAuthCodeLimit(String email, Action action) {
        this.email = email;
        this.action = action;
        this.ttl = 3600 * 2L;
        this.count = 1;
    }

    public UserAuthCodeLimit addCount() {
        this.count++;
        return this;
    }
}