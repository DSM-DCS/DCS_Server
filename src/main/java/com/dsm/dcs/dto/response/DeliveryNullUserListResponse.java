package com.dsm.dcs.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class DeliveryNullUserListResponse {

    private List<DeliveryNullUserResponse> deliveryNullUserResponses;

    @Getter
    public static class DeliveryNullUserResponse {

        private final Long id;
        private final String courierCompany;
        private final String phoneNumber;
        private final LocalDate createdDate;

        @Builder
        public DeliveryNullUserResponse(Long id, String courierCompany, String phoneNumber, LocalDate createdDate) {
            this.id = id;
            this.courierCompany = courierCompany;
            this.phoneNumber = phoneNumber;
            this.createdDate = createdDate;
        }


    }

}
