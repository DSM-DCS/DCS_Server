package com.dsm.dcs.entity.delivery;

import com.dsm.dcs.entity.BaseTimeEntity;
import com.dsm.dcs.entity.CourierCompany;
import com.dsm.dcs.entity.receipt.Receipt;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Delivery extends BaseTimeEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourierCompany courierCompany;

    @Column(nullable = false, length = 10)
    private String recipientName;

    @Column(nullable = false, length = 11)
    private String recipientPhoneNumber;

    @Column(nullable = false, length = 30)
    private String product;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Receipt receipt;

    @Builder
    public Delivery(String recipientName, String recipientPhoneNumber, String product, CourierCompany courierCompany) {
        this.recipientName = recipientName;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.product = product;
        this.courierCompany = courierCompany;
    }

}
