package com.dsm.dcs.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CourierCompany {

    LOGEN("로젠택배"), CJ("CJ대한통운"), POST("우체국택배"), LOTTE("롯데택배"), HANJIN("한진택배");

    private final String courierCompany;

}
