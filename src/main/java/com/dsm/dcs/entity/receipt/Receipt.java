package com.dsm.dcs.entity.receipt;

import com.dsm.dcs.entity.BaseTimeEntity;
import com.dsm.dcs.entity.delivery.Delivery;
import com.dsm.dcs.entity.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "receipt_confirmation")
@Entity
public class Receipt extends BaseTimeEntity {

    @Column(nullable = false, length = 30)
    private String product;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Builder
    public Receipt(String product, Delivery delivery) {
        this.product = product;
        this.delivery = delivery;
    }

}
