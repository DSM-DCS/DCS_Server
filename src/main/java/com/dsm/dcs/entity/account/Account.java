package com.dsm.dcs.entity.account;

import com.dsm.dcs.entity.BaseIdEntity;
import com.dsm.dcs.entity.Role;
import com.dsm.dcs.entity.delivery.Delivery;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Account extends BaseIdEntity {

    @Column(nullable = false, unique = true, length = 20)
    private String accountId;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, unique = true, length = 13)
    private String phoneNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Delivery> deliveries;

    @Builder
    public Account(String accountId, String password, String name, String phoneNumber, Role role) {
        this.accountId = accountId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public Account updatePassword(String password) {
        this.password = password;
        return this;
    }

}
