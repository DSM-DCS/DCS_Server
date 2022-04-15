package com.dsm.dcs.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class DeliveryIdListResponse {

    List<DeliveryIdResponse> deliveryIdResponses = new ArrayList<>();

    @AllArgsConstructor
    @Builder
    public class DeliveryIdResponse{

        private final Long DeliveryId;

    }

}
