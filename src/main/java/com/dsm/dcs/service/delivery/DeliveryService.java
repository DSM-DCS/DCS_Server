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
import com.dsm.dcs.entity.deviceToken.DeviceToken;
import com.dsm.dcs.entity.user.User;
import com.dsm.dcs.exception.FireBaseException;
import com.dsm.dcs.exception.ForbiddenException;
import com.dsm.dcs.facade.AdminFacade;
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
    private final AdminFacade adminFacade;

    public void saveDelivery(DeliveryListRequest request) {
        adminFacade.getRoleCourier();
        for (DeliveryListRequest.PhoneNumberRequest phoneNumberRequest : request.getPhoneNumberRequestList()) {
            Account account = userFacade.getUserByPhoneNumber(phoneNumberRequest.getPhoneNumber());
            deliveryRepository.save(
                    Delivery.builder()
                            .courierCompany(CourierCompany.valueOf(request.getCouriercompany()))
                            .phoneNumber(phoneNumberRequest.getPhoneNumber())
                            .account(account)
                            .build()
            );
            try {
                DeviceToken deviceToken = deviceTokenFacade.findByDeviceToken(user.getAccountId());
                if(deviceToken.getDeviceToken() != null) {
                    fireBaseService.sendMessageTo(deviceToken.getDeviceToken(), "DCS", "택배가 기숙사로 배송이 완료되었습니다.");
                }
            } catch (IOException e) {
                throw FireBaseException.EXCEPTION;
            }
        }

    }

    public DeliveryIdListResponse.DeliveryIdResponse updateDeliveryUser(Long userId, Long deliveryId) {
        adminFacade.getRoleTeacher();
        User user = userFacade.getUserById(userId);
        Delivery delivery = deliveryFacade.getDeliveryById(deliveryId);
        delivery.updateUser(account);
        deliveryRepository.save(delivery);
        return new DeliveryIdListResponse.DeliveryIdResponse(deliveryId);

    }

    public void deleteDelivery(Long deliveryId) {
        adminFacade.getRoleTeacher();
        deliveryFacade.deleteDelivery(deliveryId);
    }

    public DeliveryListResponse getMyDeliveryList(Pageable page) {
        userFacade.getRole();
        return deliveryFacade.getDeliveryList(userFacade.getCurrentUser(), page);
    }

    public DeliveryListResponse getDeliveryList(Pageable page) {
        adminFacade.getRoleTeacher();
        return deliveryFacade.getDeliveryUserNotNullList(page);
    }

    public DeliveryNullUserListResponse getDeliveryUserNullList(Pageable page) {
        if(!adminFacade.getRoleTeacherBoolean() && !userFacade.getRoleBoolean()) {
            throw ForbiddenException.EXCEPTION;
        }
        return deliveryFacade.getDeliveryUserNullList(page);
    }

    public DeliveryResponse getDelivery(Long deliveryId) {
        if(!adminFacade.getRoleTeacherBoolean() && !userFacade.getRoleBoolean()) {
            throw ForbiddenException.EXCEPTION;
        }
        Delivery delivery = deliveryFacade.getDeliveryById(deliveryId);
        return DeliveryResponse.builder()
                .name(delivery.getAccount().getName())
                .id(delivery.getId())
                .createdDate(delivery.getCreatedDate())
                .phoneNumber(delivery.getAccount().getPhoneNumber())
                .courierCompany(delivery.getCourierCompany().name())
                .build();
    }

}
