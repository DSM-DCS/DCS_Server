package com.dsm.dcs.service.auth;

import com.dsm.dcs.dto.request.PhoneNumberRequest;
import com.dsm.dcs.dto.request.SmsRequest;
import com.dsm.dcs.entity.authCode.UserAuthCode;
import com.dsm.dcs.entity.authCode.UserAuthCodeRepository;
import com.dsm.dcs.exception.PhoneNumberNotFoundException;
import com.dsm.dcs.exception.AuthCodeNotMatchException;
import com.dsm.dcs.sms.SmsMessageService;
import com.dsm.dcs.utils.RandomCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SmsService {

    private final SmsMessageService smsMessageService;
    private final UserAuthCodeRepository userAuthCodeRepository;

    public void sendMessageAuthCode(PhoneNumberRequest request) {
        String randomNumber = RandomCodeUtil.generateRandomCode(6);
        userAuthCodeRepository.save(UserAuthCode.builder()
                .phoneNumber(request.getPhoneNumber())
                .code(randomNumber).build());
        smsMessageService.sendMessage(request.getPhoneNumber(), randomNumber);
    }

    public void checkCoincideAuthCode(SmsRequest request) {
        UserAuthCode userAuthCode = userAuthCodeRepository.findById(request.getPhoneNumber())
                .orElseThrow(() -> PhoneNumberNotFoundException.EXCEPTION);
        if (!userAuthCode.getCode().equals(request.getCode())) {
            throw AuthCodeNotMatchException.EXCEPTION;
        }
    }

}
