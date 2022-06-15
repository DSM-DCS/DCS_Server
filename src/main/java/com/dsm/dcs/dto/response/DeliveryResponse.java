package com.dsm.dcs.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class DeliveryResponse {

    private final Long id;
    private final String courierCompany;
    private final String name;
    private final LocalDate createdDate;
    private final String phoneNumber;
    private final Integer studentNumber;


}
