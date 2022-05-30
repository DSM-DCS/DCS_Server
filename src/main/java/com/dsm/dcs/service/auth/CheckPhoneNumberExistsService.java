package com.dsm.dcs.service.auth;

import com.dsm.dcs.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckPhoneNumberExistsService {

    private final UserFacade userFacade;

    public void execute(String phoneNumber) {
        userFacade.checkPhoneNumberExists(phoneNumber);
    }
}
