package com.dsm.dcs.service.user;

import com.dsm.dcs.dto.request.VerifyAuthCodeRequest;
import com.dsm.dcs.entity.user.UserAuthCode;
import com.dsm.dcs.entity.user.UserAuthCodeRepository;
import com.dsm.dcs.exception.InvalidAuthCodeException;
import com.dsm.dcs.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VerificationAuthCodeService {

    private final UserFacade userFacade;
    private final UserAuthCodeRepository userAuthCodeRepository;

    @Transactional
    public void execute(VerifyAuthCodeRequest request) {
        String email = request.getEmail();
        String code = request.getCode();

        UserAuthCode userAuthCode = userFacade.getAuthCodeById(email);

        Optional.of(userAuthCode)
                .filter(s -> userFacade.isAlreadyVerified(s.isVerify()))
                .filter(s -> userFacade.compareCode(code, s.getCode()))
                .map(UserAuthCode::verify)
                .map(userAuthCodeRepository::save)
                .orElseThrow(() -> InvalidAuthCodeException.EXCEPTION);
    }

}
