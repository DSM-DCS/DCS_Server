package com.dsm.dcs.entity.user.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class UserListInfoVO {

    private final Long userId;
    private final String name;
    private final Integer studentNumber;
    private final Boolean isTeacher;

    @QueryProjection
    public UserListInfoVO(Long userId, String name, Integer studentNumber, Boolean isTeacher) {
        this.userId = userId;
        this.name = name;
        this.studentNumber = studentNumber;
        this.isTeacher = isTeacher;
    }
}
