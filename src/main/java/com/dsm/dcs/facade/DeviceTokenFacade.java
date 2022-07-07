package com.dsm.dcs.facade;

import com.dsm.dcs.entity.deviceToken.DeviceToken;
import com.dsm.dcs.entity.deviceToken.DeviceTokenRepository;
import com.dsm.dcs.exception.DeviceTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class DeviceTokenFacade {

    private DeviceTokenRepository deviceTokenRepository;

    public DeviceToken findByDeviceToken(String accountId) {
        return deviceTokenRepository.findById(accountId)
                .orElseThrow(() -> DeviceTokenNotFoundException.EXCEPTION);
    }

}
