package com.dsm.dcs.service.user;

import com.dsm.dcs.dto.request.SendEmailRequest;
import com.dsm.dcs.entity.Action;
import com.dsm.dcs.facade.UserAuthCodeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SendIdAuthCodeService {

    private final UserAuthCodeFacade authCodeFacade;

    @Transactional
    public void execute(SendEmailRequest request) {

        authCodeFacade.sendEmail(request.getEmail(), Action.FIND_ID);

    }
    
}
