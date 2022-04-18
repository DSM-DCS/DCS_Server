package com.dsm.dcs.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@AllArgsConstructor
public class DeliveryIdListResponse {

    private final List<DeliveryIdResponse> deliveryIdResponses;

    @AllArgsConstructor
    @Builder
    public static class DeliveryIdResponse{

        private final Long DeliveryId;

    }

}
