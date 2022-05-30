package com.dsm.dcs.service.user;

import com.dsm.dcs.dto.request.SendEmailRequest;
import com.dsm.dcs.dto.response.SendEmailAuthCodeResponse;
import com.dsm.dcs.entity.Action;
import com.dsm.dcs.entity.user.*;
import com.dsm.dcs.exception.*;
import com.dsm.dcs.infra.ses.SesUtil;
import com.dsm.dcs.utils.RandomCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SendEmailAuthCodeService {

    private final UserRepository userRepository;
    private final UserAuthCodeRepository userAuthCodeRepository;
    private final UserAuthCodeLimitRepository userAuthCodeLimitRepository;
    private final SesUtil sesUtil;

    @Transactional
    public SendEmailAuthCodeResponse execute(SendEmailRequest request) {

        Action action = request.getAction();
        String value = request.getValue();
        String email = getEmail(action, value);

        Optional<UserAuthCode> AuthCode = userAuthCodeRepository.findByEmailAndAction(email, action);

        AuthCode.ifPresent(code -> checkVerify(code.isVerify()));
        isOverLimit(email, action);

        String authCode = RandomCodeUtil.make(6);

        AuthCode.ifPresentOrElse(
                code -> code.updateAuthCode(authCode),
                () -> buildAuthCode(email, authCode, request.getAction())
        );

        sesUtil.sendMail(email, authCode);

        return new SendEmailAuthCodeResponse(email);
    }

    private String getEmail(Action action, String value) {
        if (Action.UPDATE_PASSWORD.equals(action)) {
            return userRepository.findByAccountId(value)
                    .orElseThrow(() -> UserNotFoundException.EXCEPTION)
                    .getEmail();
        } else {
            return value;
        }
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
