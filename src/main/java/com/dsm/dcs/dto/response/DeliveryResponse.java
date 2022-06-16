package com.dsm.dcs.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DeliveryResponse {

    private final Long id;
    private final String courierCompany;
    private final String name;
    private final LocalDate createdDate;
    private final String phoneNumber;
    private final Integer studentNumber;

    @Builder
    public DeliveryResponse(Long id, String courierCompany, String name, LocalDate createdDate, String phoneNumber, Integer studentNumber) {
        this.id = id;
        this.courierCompany = courierCompany;
        this.name = name;
        this.createdDate = createdDate;
        this.phoneNumber = phoneNumber;
        this.studentNumber = studentNumber;
    }



}
