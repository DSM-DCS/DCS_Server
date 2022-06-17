package com.dsm.dcs.service.auth;

import com.dsm.dcs.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckExistsService {

    private final UserFacade userFacade;

    public void checkEmailExists(String email) {
        userFacade.checkEmailExists(email);
    }

    public void checkPhoneNumberExists(String phoneNumber) {
        userFacade.checkPhoneNumberExists(phoneNumber);
    }

    public void checkStudentNumberExists(Integer studentNumber) {
        userFacade.checkStudentNumberExists(studentNumber);
    }

    public void checkAccountIdExists(String accountId) {
        userFacade.checkUserExists(accountId);
    }

}
