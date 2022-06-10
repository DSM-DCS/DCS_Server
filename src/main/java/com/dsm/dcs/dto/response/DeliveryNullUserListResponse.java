package com.dsm.dcs.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class DeliveryNullUserListResponse {

    List<DeliveryNullUserResponse> deliveryNullUserResponses;

    @Getter
    @Builder
    public static class DeliveryNullUserResponse {

        private final Long id;
        private final String courierCompany;
        private final String phoneNumber;
        private final LocalDate createdDate;

    }

}
