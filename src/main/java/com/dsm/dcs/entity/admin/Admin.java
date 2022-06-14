package com.dsm.dcs.entity.admin;

import com.dsm.dcs.entity.BaseTimeEntity;
import com.dsm.dcs.entity.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Admin extends BaseTimeEntity {

    @Column(nullable = false, unique = true, length = 20)
    private String adminId;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Admin(String adminId, String password, Role role) {
        this.adminId = adminId;
        this.password = password;
        this.role = role;
    }

    public Admin updatePassword(String password) {
        this.password = password;
        return this;
    }

}
