package com.dsm.dcs.entity.image;

import com.dsm.dcs.global.entity.BaseIdEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Image extends BaseIdEntity {

    @Column(nullable = false)
    private String path;

}
