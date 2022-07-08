package com.dsm.dcs.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserListResponse {

    private List<UserResponse> userResponses;

    @Getter
    public static class UserResponse {

        private final String name;

        public UserResponse(String name) {
            this.name = name;
        }

    }


}
