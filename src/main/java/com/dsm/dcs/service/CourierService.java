package com.dsm.dcs.service;

import com.dsm.dcs.dto.request.DeliveryListRequest;
import com.dsm.dcs.dto.response.DeliveryIdListResponse;
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

            for (DeliveryListRequest.DeliveryWaybillNumberRequest delivery : request.getDeliverywaybillnumberrequestlist()) {
                deliveryIdResponses.add(
                        new DeliveryIdListResponse.DeliveryIdResponse(
                                deliveryRepository.save(
                                        Delivery.builder()
                                                .build()
                                ).getId()
                        )
                );
            }

        return new DeliveryIdListResponse(deliveryIdResponses);

    }


}
