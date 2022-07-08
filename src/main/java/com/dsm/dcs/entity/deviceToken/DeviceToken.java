package com.dsm.dcs.entity.deviceToken;

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
public class DeviceToken {

    @Id
    private String accountId;

    private String deviceToken;

    @Builder
    public DeviceToken(String accountId, String deviceToken) {
        this.accountId = accountId;
        this.deviceToken = deviceToken;
    }

}
