package com.dsm.dcs.entity.user;

import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

@EnableRedisRepositories
public interface UserAuthCodeLimitRepository extends CrudRepository<UserAuthCodeLimit, String> {
}
