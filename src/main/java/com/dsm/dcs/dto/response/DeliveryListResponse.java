package com.dsm.dcs.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class DeliveryListResponse {

    private List<DeliveryResponse> deliveryResponses;

    @Getter
    public static class DeliveryResponse {

        private final Long id;
        private final String courierCompany;
        private final String name;
        private final String phoneNumber;
        private final String products;
        private final LocalDate createdDate;

        @Builder
        public DeliveryResponse(Long id, String courierCompany, String name, String phoneNumber, String products, LocalDate createdDate) {
            this.id = id;
            this.courierCompany = courierCompany;
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.products = products;
            this.createdDate = createdDate;
        }

    }

}
