package com.dsm.dcs.entity.auth;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RefreshToken {

    @Id
    private String accountId;

    @Column(nullable = false)
    private String refreshToken;


    @Builder
    public RefreshToken(String accountId, String refreshToken) {
        this.accountId = accountId;
        this.refreshToken = refreshToken;
    }

    public RefreshToken updateToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

}
