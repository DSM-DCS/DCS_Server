package com.dsm.dcs.service.auth;

import com.dsm.dcs.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckEmailExistsService {

    private final UserFacade userFacade;

    public void execute(String email) {
        userFacade.checkEmailExists(email);
    }
}
