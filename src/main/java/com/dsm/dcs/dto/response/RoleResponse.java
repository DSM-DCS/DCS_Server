package com.dsm.dcs.dto.response;

import lombok.Getter;

@Getter
public class RoleResponse {

    private final String role;

    public RoleResponse(String role){
        this.role = role;
    }

}
