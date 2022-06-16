package com.dsm.dcs.firebase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class FcmMessage {
    private boolean validateOnly;
    private Message message;

    @Builder
    @Getter
    public static class Message {
        private Notification notification;
        private String token;
    }

    @Getter
    public static class Notification {
        private String title;
        private String body;
        private String image;

        @Builder
        public Notification(String title, String body, String image) {
            this.title = title;
            this.body = body;
            this.image = image;
        }

    }
}
