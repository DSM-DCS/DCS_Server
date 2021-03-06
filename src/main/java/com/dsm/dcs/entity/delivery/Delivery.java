package com.dsm.dcs.entity.delivery;

import com.dsm.dcs.entity.BaseTimeEntity;
import com.dsm.dcs.entity.CourierCompany;
import com.dsm.dcs.entity.account.Account;
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

    @Column(nullable = false, length = 13)
    private String phoneNumber;

    @Column(nullable = false, length = 20)
    private String products;

    @Column(nullable = false)
    private Boolean isReceipt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Builder
    public Delivery(Long id, String phoneNumber, CourierCompany courierCompany, String products,Boolean isReceipt, Account account) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.courierCompany = courierCompany;
        this.products = products;
        this.isReceipt = isReceipt;
        this.account = account;
    }

    public void updateUser(Account account) {
        this.account = account;
    }

    public void isReceipt() {
        this.isReceipt = true;
    }

}
