package com.dsm.dcs.entity.user;

import com.dsm.dcs.entity.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(String accountId);
}
