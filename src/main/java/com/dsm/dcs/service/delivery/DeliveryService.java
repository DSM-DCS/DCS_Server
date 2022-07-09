package com.dsm.dcs.service.delivery;

import com.dsm.dcs.dto.request.DeliveryListRequest;
import com.dsm.dcs.dto.response.DeliveryIdListResponse;
import com.dsm.dcs.dto.response.DeliveryListResponse;
import com.dsm.dcs.dto.response.DeliveryNullUserListResponse;
import com.dsm.dcs.dto.response.DeliveryResponse;
import com.dsm.dcs.entity.CourierCompany;
import com.dsm.dcs.entity.account.Account;
import com.dsm.dcs.entity.delivery.Delivery;
import com.dsm.dcs.entity.delivery.DeliveryRepository;
import com.dsm.dcs.exception.ForbiddenException;
import com.dsm.dcs.facade.DeliveryFacade;
import com.dsm.dcs.facade.DeviceTokenFacade;
import com.dsm.dcs.facade.UserFacade;
import com.dsm.dcs.firebase.FireBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Transactional
@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final FireBaseService fireBaseService;
    private final DeliveryFacade deliveryFacade;
    private final DeviceTokenFacade deviceTokenFacade;
    private final UserFacade userFacade;

    public void saveDelivery(DeliveryListRequest request) {
        userFacade.checkRoleCourier();
        for (DeliveryListRequest.deliveryRequest deliveryRequest : request.getDeliveryRequestList()) {
            Account account = userFacade.getUserByPhoneNumber(deliveryRequest.getPhoneNumber());
            deliveryRepository.save(
                    Delivery.builder()
                            .courierCompany(CourierCompany.valueOf(request.getCouriercompany()))
                            .phoneNumber(account.getPhoneNumber())
                            .products(deliveryRequest.getProducts())
                            .account(account)
                            .build()
            );

            if(isAccount(account)) {
                deviceTokenFacade.sendMessageTo(account.getAccountId());
            }
        }
    }

    public DeliveryIdListResponse.DeliveryIdResponse updateDeliveryUser(Long userId, Long deliveryId) {
        userFacade.checkRoleAdmin();
        Account account = userFacade.getUserById(userId);
        Delivery delivery = deliveryFacade.getDeliveryById(deliveryId);
        delivery.updateUser(account);
        deliveryRepository.save(delivery);
        return new DeliveryIdListResponse.DeliveryIdResponse(deliveryId);

    }

    public void deleteDelivery(Long deliveryId) {
        userFacade.checkRoleAdmin();
        deliveryFacade.deleteDelivery(deliveryId);
    }

    public DeliveryListResponse getMyDeliveryList(Pageable page) {
        userFacade.checkRoleUser();
        return deliveryFacade.getDeliveryList(userFacade.getCurrentUser(), page);
    }

    public DeliveryListResponse getDeliveryList(Pageable page) {
        userFacade.checkRoleAdmin();
        return deliveryFacade.getDeliveryUserNotNullList(page);
    }

    public DeliveryNullUserListResponse getDeliveryUserNullList(Pageable page) {
        if(!userFacade.isAdmin() && !userFacade.isUser()) {
            throw ForbiddenException.EXCEPTION;
        }
        return deliveryFacade.getDeliveryUserNullList(page);
    }

}
