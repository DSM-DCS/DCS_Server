package com.dsm.dcs.entity.delivery;

import com.dsm.dcs.entity.BaseTimeEntity;
import com.dsm.dcs.entity.receipt.Receipt;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Delivery extends BaseTimeEntity {

    @Column(nullable = false)
    private Integer courierCompany;

    @Column(nullable = false )
    private Integer waybillNumber;

    @Column(nullable = false)
    private String product;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Receipt receipt;

    @Builder
    public Delivery(Long id, Integer waybillNumber, String product, Integer courierCompany) {
        this.id = id;
        this.waybillNumber = waybillNumber;
        this.product = product;
        this.courierCompany = courierCompany;
    }

}
