package com.dsm.dcs.service.auth;

import com.dsm.dcs.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckStudentNumberExistsService {

    private final UserFacade userFacade;

    public void execute(Integer studentNumber) {
        userFacade.checkStudentNumberExists(studentNumber);
    }
}
