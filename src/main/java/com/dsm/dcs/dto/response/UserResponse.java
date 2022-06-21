package com.dsm.dcs.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {

    private final String name;
    private final String accountId;
    private final String email;
    private final Integer studentNumber;
    private final String phoneNumber;

    @Builder
    public UserResponse(String name, String accountId, String email, Integer studentNumber, String phoneNumber) {
        this.name = name;
        this.accountId = accountId;
        this.email = email;
        this.studentNumber = studentNumber;
        this.phoneNumber = phoneNumber;
    }

}
