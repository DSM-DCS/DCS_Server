package com.dsm.dcs.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DeliveryIdListResponse {

    private final List<DeliveryIdResponse> deliveryIdResponses;

    @Getter
    public static class DeliveryIdResponse{

        private final Long deliveryId;

        public DeliveryIdResponse(Long deliveryId) {
            this.deliveryId = deliveryId;
        }

    }

}
