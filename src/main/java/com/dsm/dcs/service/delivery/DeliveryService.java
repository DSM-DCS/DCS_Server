package com.dsm.dcs.service.delivery;

import com.dsm.dcs.dto.request.DeliveryListRequest;
import com.dsm.dcs.dto.response.DeliveryIdListResponse;
import com.dsm.dcs.dto.response.DeliveryListResponse;
import com.dsm.dcs.dto.response.DeliveryNullUserListResponse;
import com.dsm.dcs.entity.CourierCompany;
import com.dsm.dcs.entity.delivery.Delivery;
import com.dsm.dcs.entity.delivery.DeliveryRepository;
import com.dsm.dcs.entity.user.User;
import com.dsm.dcs.exception.FireBaseException;
import com.dsm.dcs.exception.handler.DcsException;
import com.dsm.dcs.facade.DeliveryFacade;
import com.dsm.dcs.facade.DeviceTokenFacade;
import com.dsm.dcs.facade.UserFacade;
import com.dsm.dcs.firebase.FireBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final FireBaseService fireBaseService;
    private final DeliveryFacade deliveryFacade;
    private final DeviceTokenFacade deviceTokenFacade;
    private final UserFacade userFacade;

    public DeliveryIdListResponse saveDelivery(DeliveryListRequest request) {

        List<DeliveryIdListResponse.DeliveryIdResponse> deliveryIdResponses = new ArrayList<>();

        for (DeliveryListRequest.PhoneNumberRequest phoneNumberRequest : request.getPhoneNumberRequestList()) {
            User user = userFacade.getUserByPhoneNumber(phoneNumberRequest.getPhoneNumber());
            deliveryIdResponses.add(
                    new DeliveryIdListResponse.DeliveryIdResponse(
                            deliveryRepository.save(
                                    Delivery.builder()
                                            .courierCompany(CourierCompany.valueOf(request.getCouriercompany()))
                                            .phoneNumber(phoneNumberRequest.getPhoneNumber())
                                            .user(user)
                                            .build()
                            ).getId()
                    )
            );
            try {
                fireBaseService.sendMessageTo(deviceTokenFacade.findByDeviceToken(user.getAccountId()).getDeviceToken(), "DCS", "택배가 기숙사로 배송이 완료되었습니다.");
            } catch (IOException e) {
                throw FireBaseException.EXCEPTION;
            }
        }

        return new DeliveryIdListResponse(deliveryIdResponses);

    }

    public DeliveryIdListResponse.DeliveryIdResponse updateDeliveryUser(Long userId, Long deliveryId) {

        User user = userFacade.getUserById(userId);
        Delivery delivery = deliveryFacade.getDeliveryById(deliveryId);
        delivery.updateUser(user);
        deliveryRepository.save(delivery);
        return new DeliveryIdListResponse.DeliveryIdResponse(deliveryId);

    }

    public void deleteDelivery(Long deliveryId) {
        deliveryFacade.deleteDelivery(deliveryId);
    }

    public DeliveryListResponse getMyDeliveryList(Pageable page) {
        return deliveryFacade.getDeliveryList(userFacade.getCurrentUser(), page);
    }

    public DeliveryListResponse getDeliveryList(Pageable page) {
        return deliveryFacade.getDeliveryUserNotNullList(page);
    }

    public DeliveryNullUserListResponse getDeliveryUserNullList(Pageable page) {
        return deliveryFacade.getDeliveryUserNullList(page);
    }

    public DeliveryListResponse.DeliveryResponse getDelivery(Long deliveryId) {
        Delivery delivery = deliveryFacade.getDeliveryById(deliveryId);
        return DeliveryListResponse.DeliveryResponse.builder()
                .name(delivery.getUser().getName())
                .id(delivery.getId())
                .createdDate(delivery.getCreatedDate())
                .courierCompany(delivery.getCourierCompany().name())
                .build();
    }

}
