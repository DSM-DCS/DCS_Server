package com.dsm.dcs.entity.user;

import com.dsm.dcs.entity.BaseIdEntity;
import com.dsm.dcs.entity.Authority;
import com.dsm.dcs.entity.receipt.Receipt;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseIdEntity {

    @Column(nullable = false, unique = true, length = 20)
    private String accountId;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, unique = true, length = 35)
    private String email;

    @Column(nullable = false, unique = true, columnDefinition = "tinyint")
    private Integer studentNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "user")
    private List<Receipt> receipt;

    @Builder
    public User(String accountId, String password, String name, String email, Integer studentNumber, Authority authority) {
        this.accountId = accountId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.studentNumber = studentNumber;
        this.authority = authority;
    }
}
