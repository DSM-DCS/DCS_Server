package com.dsm.dcs.service.teacher;

import com.dsm.dcs.dto.response.DeliveryIdListResponse;
import com.dsm.dcs.dto.response.DeliveryListResponse;
import com.dsm.dcs.entity.CourierCompany;
import com.dsm.dcs.entity.delivery.Delivery;
import com.dsm.dcs.entity.delivery.DeliveryRepository;
import com.dsm.dcs.entity.user.User;
import com.dsm.dcs.facade.DeliveryFacade;
import com.dsm.dcs.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeacherService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryFacade deliveryFacade;
    private final UserFacade userFacade;

    public DeliveryIdListResponse.DeliveryIdResponse updateUser(Long userId, Long deliveryId) {

        User user = userFacade.getUserById(userId);
        Delivery delivery = deliveryFacade.getDeliveryById(deliveryId);
        delivery.updateUser(user);
        deliveryRepository.save(delivery);
        return new DeliveryIdListResponse.DeliveryIdResponse(deliveryId);

    }

    public void deleteDelivery(Long deliveryId) {
        deliveryFacade.deleteDelivery(deliveryId);
    }

    public DeliveryListResponse getDeliveryList() {

        List<DeliveryListResponse.DeliveryResponse> deliveryResponses = new ArrayList<>();
        List<Delivery> deliveries = deliveryFacade.getDeliveryList();

        for(Delivery delivery : deliveries) {
            if(delivery.getUser() != null) {
                deliveryResponses.add(
                        DeliveryListResponse.DeliveryResponse.builder()
                                .courierCompany(delivery.getCourierCompany().name())
                                .name(delivery.getUser().getName())
                                .createdDate(delivery.getCreatedDate())
                                .build()
                );
            }
        }

        return new DeliveryListResponse(deliveryResponses);

    }

}
