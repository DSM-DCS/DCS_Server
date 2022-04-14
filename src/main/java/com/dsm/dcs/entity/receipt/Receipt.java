package com.dsm.dcs.entity.receipt;

import com.dsm.dcs.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "receipt_confirmation")
@Entity
public class Receipt extends BaseTimeEntity {

    @Column(nullable = false, length = 30)
    private String product;

}
