package com.dsm.dcs.service.delivery;

import com.dsm.dcs.dto.request.DeliveryListRequest;
import com.dsm.dcs.dto.response.DeliveryIdListResponse;
import com.dsm.dcs.dto.response.DeliveryListResponse;
import com.dsm.dcs.dto.response.DeliveryNullUserListResponse;
import com.dsm.dcs.dto.response.DeliveryResponse;
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

@Transactional
@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryFacade deliveryFacade;
    private final UserFacade userFacade;

    public void saveDelivery(DeliveryListRequest request) {

        for (DeliveryListRequest.PhoneNumberRequest phoneNumberRequest : request.getPhoneNumberRequestList()) {
            deliveryRepository.save(
                    Delivery.builder()
                            .courierCompany(CourierCompany.valueOf(request.getCouriercompany()))
                            .phoneNumber(phoneNumberRequest.getPhoneNumber())
                            .user(userFacade.getUserByPhoneNumber(phoneNumberRequest.getPhoneNumber()))
                            .build()
            );
        }

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

    public DeliveryResponse getDelivery(Long deliveryId) {
        Delivery delivery = deliveryFacade.getDeliveryById(deliveryId);
        return DeliveryResponse.builder()
                .name(delivery.getUser().getName())
                .id(delivery.getId())
                .createdDate(delivery.getCreatedDate())
                .studentNumber(delivery.getUser().getStudentNumber())
                .phoneNumber(delivery.getUser().getPhoneNumber())
                .courierCompany(delivery.getCourierCompany().name())
                .build();
    }

}
