package com.dsm.dcs.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostIdListResponse {

    private final List<PostIdResponse> postIdResponses;

    @Getter
    public class PostIdResponse {

        private final Long id;

        public PostIdResponse(Long id) {
            this.id = id;
        }

    }
}
