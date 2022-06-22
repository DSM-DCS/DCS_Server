package com.dsm.dcs.facade;

import com.dsm.dcs.dto.response.SendEmailAuthCodeResponse;
import com.dsm.dcs.entity.Action;
import com.dsm.dcs.entity.user.UserAuthCode;
import com.dsm.dcs.entity.user.UserAuthCodeLimit;
import com.dsm.dcs.entity.user.UserAuthCodeLimitRepository;
import com.dsm.dcs.entity.user.UserAuthCodeRepository;
import com.dsm.dcs.exception.AuthCodeAlreadyVerifiedException;
import com.dsm.dcs.exception.AuthCodeRequestOverLimitException;
import com.dsm.dcs.infra.ses.SesUtil;
import com.dsm.dcs.utils.RandomCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAuthCodeFacade {

    private final UserAuthCodeRepository userAuthCodeRepository;
    private final UserAuthCodeLimitRepository userAuthCodeLimitRepository;
    private final SesUtil sesUtil;

    public void sendEmail(String email, Action action) {

        Optional<UserAuthCode> AuthCode = userAuthCodeRepository.findByEmailAndAction(email, action);

        AuthCode.ifPresent(code -> checkVerify(code.isVerify()));
        isOverLimit(email, action);

        String authCode = getRandomCode();

        AuthCode.ifPresentOrElse(
                code -> code.updateAuthCode(authCode),
                () -> buildAuthCode(email, authCode, action)
        );

        sesUtil.sendMail(email, authCode);

        new SendEmailAuthCodeResponse(email);
    }

    private String getRandomCode() {
        return RandomCodeUtil.make(6);
    }

    private void checkVerify(boolean isVerify) {
        if (isVerify) {
            throw AuthCodeAlreadyVerifiedException.EXCEPTION;
        }
    }

    private void isOverLimit(String email, Action action) {
        userAuthCodeLimitRepository.findById(email)
                .filter(limit -> checkCount(limit.getCount()))
                .map(UserAuthCodeLimit::addCount)
                .map(userAuthCodeLimitRepository::save)
                .orElseGet(() -> userAuthCodeLimitRepository.save(UserAuthCodeLimit.builder()
                        .email(email)
                        .action(action)
                        .build()));
    }

    private boolean checkCount(int count) {
        if (count >= 1000) {
            throw AuthCodeRequestOverLimitException.EXCEPTION;
        }
        return true;
    }

    private void buildAuthCode(String email, String authCode, Action action) {
        userAuthCodeRepository.save(
                UserAuthCode.builder()
                        .email(email)
                        .code(authCode)
                        .action(action)
                        .build()
        );
    }

}
