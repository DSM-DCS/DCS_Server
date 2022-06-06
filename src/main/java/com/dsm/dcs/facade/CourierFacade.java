package com.dsm.dcs.facade;

import com.dsm.dcs.entity.courier.Courier;
import com.dsm.dcs.entity.courier.CourierRepository;
import com.dsm.dcs.exception.AccountIdAlreadyExistsException;
import com.dsm.dcs.exception.CourierNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourierFacade {

    private final CourierRepository courierRepository;

    public Courier getUserByAccountId(String accountId) {
        return courierRepository.findByAccountId(accountId)
                .orElseThrow(() -> CourierNotFoundException.EXCEPTION);
    }

    public void checkUserExists(String accountId) {
        if (courierRepository.findByAccountId(accountId).isPresent()) {
            throw AccountIdAlreadyExistsException.EXCEPTION;
        }
    }
}
