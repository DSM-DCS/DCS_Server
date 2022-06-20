package com.dsm.dcs.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostResponse {

    private final String title;
    private final String content;
    private final LocalDate createdDate;

    @Builder
    public PostResponse(String title, String content, LocalDate createdDate) {
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }


}
