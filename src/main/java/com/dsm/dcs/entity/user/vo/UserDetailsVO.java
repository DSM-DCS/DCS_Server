package com.dsm.dcs.entity.user.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class UserDetailsVO {

    private final Long userId;
    private final String name;
    private final Integer studentNumber;

    @QueryProjection
    public UserDetailsVO(Long userId, String name, Integer studentNumber) {
        this.userId = userId;
        this.name = name;
        this.studentNumber = studentNumber;
    }

}
