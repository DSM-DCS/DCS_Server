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
    public static class PostResponse {

        private final Long id;
        private final String title;
        private final LocalDate createdDate;

        @Builder
        public PostResponse(Long id, String title, LocalDate createdDate) {
            this.id = id;
            this.title = title;
            this.createdDate = createdDate;
        }

    }

}
