package com.dsm.dcs.infra.ses;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsync;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Destination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SesUtil {

    private final AmazonSimpleEmailServiceAsync amazonSimpleEmailServiceAsync;
    private final SesProperties sesProperties;

    public void sendMail(String email, String authCode) {
        Message message = new Message()
                .withSubject(createContent("[DCS] 이메일 인증코드입니다."))
                .withBody(new Body()
                        .withHtml(createContent(authCode)));

        SendEmailRequest request = new SendEmailRequest()
                .withDestination(new Destination().withToAddresses(email))
                .withSource(sesProperties.getEmail())
                .withMessage(message);

        amazonSimpleEmailServiceAsync.sendEmailAsync(request);
    }

    public void SendEmailForPassword(String email, String result) {
        Message message = new Message()
                .withSubject(createContent("회원님의 비밀번호 "))
                .withBody(new Body()
                        .withHtml(createContent(result)));

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