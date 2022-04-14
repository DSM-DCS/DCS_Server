package com.dsm.dcs.entity.image;

import com.dsm.dcs.entity.BaseIdEntity;
import com.dsm.dcs.entity.receipt.Receipt;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Image extends BaseIdEntity {

    @Column(nullable = false)
    private String path;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    @Builder
    public Image(String path, Receipt receipt) {
        this.path = path;
        this.receipt = receipt;
    }

}
