package com.dsm.dcs.entity.account;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByAccountId(String accountId);
    Optional<Account> findByPhoneNumber(String phoneNumber);
    List<Account> findAllByOrderByIdDesc(Pageable page);
    List<Account> findAllByNameContaining(String name, Pageable page);

}
