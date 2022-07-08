package com.dsm.dcs.service.auth;

import com.dsm.dcs.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckExistsService {

    private final UserFacade userFacade;

    public void checkPhoneNumberExists(String phoneNumber) {
        userFacade.checkPhoneNumberExists(phoneNumber);
    }

    public void checkAccountIdExists(String accountId) {
        userFacade.checkUserExists(accountId);
    }

}
