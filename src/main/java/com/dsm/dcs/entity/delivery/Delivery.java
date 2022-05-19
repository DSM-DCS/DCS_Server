package com.dsm.dcs.entity.delivery;

import com.dsm.dcs.entity.BaseTimeEntity;
import com.dsm.dcs.entity.CourierCompany;
import com.dsm.dcs.entity.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Delivery extends BaseTimeEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourierCompany courierCompany;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Delivery(Long id, CourierCompany courierCompany, User user) {
        this.id = id;
        this.courierCompany = courierCompany;
        this.user = user;
    }

}
