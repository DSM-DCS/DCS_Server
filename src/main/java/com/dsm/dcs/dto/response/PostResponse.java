package com.dsm.dcs.dto.response;

import java.time.LocalDate;

public class PostResponse {

    private final String title;
    private final String content;
    private final LocalDate createdDate;

    public PostResponse(String title, String content, LocalDate createdDate) {
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }


}
