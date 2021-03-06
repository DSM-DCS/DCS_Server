package com.dsm.dcs.service.delivery;

import com.dsm.dcs.dto.request.DeliveryListRequest;
import com.dsm.dcs.dto.response.DeliveryIdListResponse;
import com.dsm.dcs.dto.response.DeliveryListResponse;
import com.dsm.dcs.dto.response.DeliveryMainListResponse;
import com.dsm.dcs.dto.response.DeliveryNotUserListResponse;
import com.dsm.dcs.entity.CourierCompany;
import com.dsm.dcs.entity.account.Account;
import com.dsm.dcs.entity.delivery.Delivery;
import com.dsm.dcs.entity.delivery.DeliveryRepository;
import com.dsm.dcs.exception.ForbiddenException;
import com.dsm.dcs.exception.RecipientNotFoundException;
import com.dsm.dcs.facade.DeliveryFacade;
import com.dsm.dcs.facade.DeviceTokenFacade;
import com.dsm.dcs.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
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
                            .phoneNumber(deliveryRequest.getPhoneNumber())
                            .products(deliveryRequest.getProducts())
                            .isReceipt(false)
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
        return new DeliveryIdListResponse.DeliveryIdResponse(deliveryRepository.save(delivery).getId());

    }

    public DeliveryIdListResponse.DeliveryIdResponse receiptDelivery(Long deliveryId) {
        userFacade.checkRoleAdmin();
        Delivery delivery = deliveryFacade.getDeliveryById(deliveryId);
        if(!isAccount(delivery.getAccount())) {
            throw RecipientNotFoundException.EXCEPTION;
        }
        delivery.isReceipt();
        return new DeliveryIdListResponse.DeliveryIdResponse(deliveryRepository.save(delivery).getId());

    }

    public DeliveryListResponse getMyDeliveryList(Pageable page) {
        userFacade.checkRoleUser();
        return deliveryFacade.getDeliveryList(userFacade.getCurrentUser(), page);
    }

    public DeliveryListResponse getReceivedDeliveryList(Pageable page) {
        userFacade.checkRoleUser();
        return deliveryFacade.getReceivedDeliveryList(userFacade.getCurrentUser(), page);
    }

    public DeliveryListResponse getDeliveryList(Pageable page) {
        userFacade.checkRoleAdmin();
        return deliveryFacade.getDeliveryList(page);
    }

    public DeliveryNotUserListResponse getNotUserDeliveryList(Pageable page) {
        if(!userFacade.isAdmin() && !userFacade.isUser()) {
            throw ForbiddenException.EXCEPTION;
        }
        return deliveryFacade.getNotUserDeliveryList(page);
    }

    public DeliveryMainListResponse getMainDeliveryList(Pageable page) {
        return deliveryFacade.getMainDeliveryList(page);
    }

    private Boolean isAccount(Account account) {
        if(account == null) {
            return false;
        }
        return true;
    }

}
