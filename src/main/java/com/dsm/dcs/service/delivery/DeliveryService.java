package com.dsm.dcs.service.delivery;

import com.dsm.dcs.dto.request.DeliveryListRequest;
import com.dsm.dcs.dto.response.DeliveryIdListResponse;
import com.dsm.dcs.dto.response.DeliveryListResponse;
import com.dsm.dcs.dto.response.DeliveryNullUserListResponse;
import com.dsm.dcs.entity.CourierCompany;
import com.dsm.dcs.entity.delivery.Delivery;
import com.dsm.dcs.entity.delivery.DeliveryRepository;
import com.dsm.dcs.entity.user.User;
import com.dsm.dcs.facade.DeliveryFacade;
import com.dsm.dcs.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryFacade deliveryFacade;
    private final UserFacade userFacade;

    public DeliveryIdListResponse saveDelivery(DeliveryListRequest request) {

        List<DeliveryIdListResponse.DeliveryIdResponse> deliveryIdResponses = new ArrayList<>();

        for (DeliveryListRequest.PhoneNumberRequest phoneNumberRequest : request.getPhoneNumberRequestList()) {
            deliveryIdResponses.add(
                    new DeliveryIdListResponse.DeliveryIdResponse(
                            deliveryRepository.save(
                                    Delivery.builder()
                                            .courierCompany(CourierCompany.valueOf(request.getCouriercompany()))
                                            .phoneNumber(phoneNumberRequest.getPhoneNumber())
                                            .user(userFacade.getUserByPhoneNumber(phoneNumberRequest.getPhoneNumber()))
                                            .build()
                            ).getId()
                    )
            );
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
