package com.dsm.dcs.sms;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SmsMessageService {

    @Value("${sms.apikey}")
    private String apiKey;

    @Value("${sms.secret}")
    private String apiSecret;

    @Value("${sms.phoneNumber}")
    private String fromPhoneNumber;

    public void sendMessage(String toPhoneNumber, String randomNumber) {

        Message coolsms = new Message(apiKey, apiSecret);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", toPhoneNumber);
        params.put("from", fromPhoneNumber);
        params.put("type", "SMS");
        params.put("text", "[DCS] 인증번호(" + randomNumber + ")입니다.");
        params.put("app_version", "test app 1.2");

        try {
            coolsms.send(params);
        } catch (CoolsmsException e) {
            e.getStackTrace();
        }
    }

}