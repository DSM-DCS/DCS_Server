package com.dsm.dcs.entity.user;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByAccountId(String accountId);
    Optional<User> findByPhoneNumber(String phoneNumber);
    List<User> findAllByOrderByIdDesc(Pageable page);
    List<User> findAllByNameContaining(String name, Pageable page);

}
