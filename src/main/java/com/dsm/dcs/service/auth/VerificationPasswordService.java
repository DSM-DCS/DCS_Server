package com.dsm.dcs.service.auth;

import com.dsm.dcs.dto.request.VerificationPasswordRequest;
import com.dsm.dcs.entity.account.Account;
import com.dsm.dcs.exception.PasswordMismatchException;
import com.dsm.dcs.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VerificationPasswordService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    public void execute(VerificationPasswordRequest request) {
        userFacade.isUser();
        Account account = userFacade.getCurrentUser();

        if (!passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }
    }
}
