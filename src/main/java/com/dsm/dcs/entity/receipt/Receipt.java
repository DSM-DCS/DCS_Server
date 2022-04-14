package com.dsm.dcs.entity.receipt;

import com.dsm.dcs.entity.BaseTimeEntity;
import com.dsm.dcs.entity.delivery.Delivery;
import com.dsm.dcs.entity.image.Image;
import com.dsm.dcs.entity.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CourierPickupConfirmation")
@Entity
public class Receipt extends BaseTimeEntity {

    @Column(nullable = false, length = 30)
    private String product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToOne(mappedBy = "receipt", fetch = FetchType.LAZY)
    private Image image;

    @Builder
    public Receipt(String product, User user, Delivery delivery) {
        this.product = product;
        this.user = user;
        this.delivery = delivery;
    }

}
