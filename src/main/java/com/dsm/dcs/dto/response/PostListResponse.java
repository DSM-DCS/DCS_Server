package com.dsm.dcs.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class PostListResponse {

    private List<PostResponse> postResponseList;

    @Getter
    public class PostResponse {

        private final String title;
        private final LocalDate createdDate;

        @Builder
        public PostResponse(String title, LocalDate createdDate) {
            this.title = title;
            this.createdDate = createdDate;
        }

    }

}
