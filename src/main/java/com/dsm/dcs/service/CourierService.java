package com.dsm.dcs.service;

import com.dsm.dcs.dto.request.DeliveryListRequest;
import com.dsm.dcs.dto.response.DeliveryIdListResponse;
import com.dsm.dcs.entity.CourierCompany;
import com.dsm.dcs.entity.delivery.Delivery;
import com.dsm.dcs.entity.delivery.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class CourierService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryIdListResponse seveDelivery(DeliveryListRequest request) {

        List<DeliveryIdListResponse.DeliveryIdResponse> deliveryIdResponses = new ArrayList<>();

        for (DeliveryListRequest.DeliveryDto delivery : request.getDelivery()) {
            deliveryIdResponses.add(
                    DeliveryIdListResponse.DeliveryIdResponse.builder()
                            .DeliveryId(
                                    deliveryRepository.save(
                                        Delivery.builder()
                                                .courierCompany(CourierCompany.valueOf(request.getCourierCompany()))
                                                .recipientName(delivery.getRecipientName())
                                                .recipientPhoneNumber(delivery.getRecipientPhoneNumber())
                                                .product(delivery.getProduct())
                                                .build()
                                    ).getId())
                            .build()
           );
        }

        return new DeliveryIdListResponse(deliveryIdResponses);
    }


}
