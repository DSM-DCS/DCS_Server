package com.dsm.dcs.facade;

import com.dsm.dcs.entity.deviceToken.DeviceToken;
import com.dsm.dcs.entity.deviceToken.DeviceTokenRepository;
import com.dsm.dcs.exception.FireBaseException;
import com.dsm.dcs.firebase.FireBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class DeviceTokenFacade {

    private final DeviceTokenRepository deviceTokenRepository;
    private final FireBaseService fireBaseService;

    public DeviceToken findByDeviceToken(String accountId) {
        return deviceTokenRepository.findById(accountId)
                .orElse(null);
    }

    public void sendMessageTo(String account) {
        DeviceToken deviceToken = findByDeviceToken(account);
        if(isDeviceToken(deviceToken.getDeviceToken())) {
            try {
                fireBaseService.sendMessageTo(deviceToken.getDeviceToken(), "DCS", "택배가 기숙사로 배송이 완료되었습니다.");
            }catch (IOException e) {
                throw FireBaseException.EXCEPTION;
            }
        }
    }

    private Boolean isDeviceToken(String deviceToken) {
        if(deviceToken == null) { return false; }
        return true;
    }

}
