package com.dsm.dcs.entity.user;

import com.dsm.dcs.entity.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByAccountId(String accountId);
    Optional<User> findByStudentNumber(Integer studentNumber);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
    List<User> findAllByOrderByStudentNumberDesc();
    List<User> findAllByNameContaining(String name);

    List<User> findAllByAuthority(@Param("authority") Authority authority);

}
