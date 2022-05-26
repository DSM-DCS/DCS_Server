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
    @Builder
    public static class UserResponse {

        private final String name;
        private final Integer studentNumber;

        public UserResponse(String name, Integer studentNumber) {
            this.name = name;
            this.studentNumber = studentNumber;
        }

    }


}
