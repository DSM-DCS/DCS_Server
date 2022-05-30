package com.dsm.dcs.infra.ses;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsync;
import com.amazonaws.services.simpleemail.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SesUtil {

    private final AmazonSimpleEmailServiceAsync amazonSimpleEmailServiceAsync;
    private final SesProperties sesProperties;

    public void sendMail(String email, String authCode) {
        Message message = new Message()
                .withSubject(createContent("test 이메일 입니다."))
                .withBody(new Body()
                        .withHtml(createContent(authCode)));

        SendEmailRequest request = new SendEmailRequest()
                .withDestination(new Destination().withToAddresses(email))
                .withSource(sesProperties.getEmail())
                .withMessage(message);

        amazonSimpleEmailServiceAsync.sendEmailAsync(request);
    }

    private Content createContent(String text) {
        return new Content()
                .withCharset("UTF-8")
                .withData(text);
    }
}