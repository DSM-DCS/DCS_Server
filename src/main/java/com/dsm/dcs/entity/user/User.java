package com.dsm.dcs.entity.user;

import com.dsm.dcs.entity.Authority;
import com.dsm.dcs.entity.BaseTimeEntity;
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
public class User extends BaseTimeEntity {

    @Column(nullable = false, unique = true, length = 20)
    private String accountId;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, unique = true, length = 35)
    private String email;

    @Column(nullable = false, unique = true, length = 13)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private Integer studentNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "user")
    private List<Delivery> deliveries;

    @Builder
    public User(String accountId, String password, String name, String email, String phoneNumber,
                Integer studentNumber, Authority authority) {
        this.accountId = accountId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.studentNumber = studentNumber;
        this.authority = authority;
    }

    public User updatePassword(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
