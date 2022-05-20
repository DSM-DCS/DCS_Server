package com.dsm.dcs.service;

import com.dsm.dcs.dto.request.DeliveryListRequest;
import com.dsm.dcs.dto.response.DeliveryIdListResponse;
import com.dsm.dcs.entity.CourierCompany;
import com.dsm.dcs.entity.delivery.Delivery;
import com.dsm.dcs.entity.delivery.DeliveryRepository;
import com.dsm.dcs.facade.UserFacade;
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
    private final UserFacade userFacade;

    public DeliveryIdListResponse seveDelivery(DeliveryListRequest request) {

        List<DeliveryIdListResponse.DeliveryIdResponse> deliveryIdResponses = new ArrayList<>();

            for (DeliveryListRequest.PhoneNumberRequest phoneNumberRequest : request.getPhoneNumberRequestList()) {
                deliveryIdResponses.add(
                        new DeliveryIdListResponse.DeliveryIdResponse(
                                deliveryRepository.save(
                                        Delivery.builder()
                                                .courierCompany(CourierCompany.valueOf(request.getCouriercompany()))
                                                .user(userFacade.getUserByPhoneNumber(phoneNumberRequest.getPhoneNumber()))
                                                .build()
                                ).getId()
                        )
                );
            }

        return new DeliveryIdListResponse(deliveryIdResponses);

    }


}
