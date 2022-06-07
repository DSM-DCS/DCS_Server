package com.dsm.dcs.entity.courier;

import com.dsm.dcs.entity.Authority;
import com.dsm.dcs.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Courier extends BaseTimeEntity {

    @Column(nullable = false, unique = true, length = 20)
    private String accountId;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Courier(String accountId, String password, Authority authority) {
        this.accountId = accountId;
        this.password = password;
        this.authority = authority;
    }

    public Courier updatePassword(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
