package com.dsm.dcs.entity.admin;

import com.dsm.dcs.entity.BaseIdEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Admin extends BaseIdEntity {

    @Column(nullable = false, unique = true, length = 20)
    private String adminId;

    @Column(nullable = false, length = 60)
    private String password;

    @Builder
    public Admin(String adminId, String password) {
        this.adminId = adminId;
        this.password = password;
    }

}
