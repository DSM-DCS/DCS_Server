package com.dsm.dcs.entity.user;

import com.dsm.dcs.entity.Action;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserAuthCodeRepository extends CrudRepository<UserAuthCode, String> {

    Optional<UserAuthCode> findByEmailAndAction(String email, Action action);
}
