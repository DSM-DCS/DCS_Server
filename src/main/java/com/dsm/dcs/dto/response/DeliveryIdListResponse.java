package com.dsm.dcs.dto.response;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class DeliveryIdListResponse {

    private final List<DeliveryIdResponse> deliveryIdResponses;

    public static class DeliveryIdResponse{

        private final Long deliveryId;

        public DeliveryIdResponse(Long deliveryId) {
            this.deliveryId = deliveryId;
        }

    }

}
