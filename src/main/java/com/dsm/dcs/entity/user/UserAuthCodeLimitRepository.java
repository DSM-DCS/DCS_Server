package com.dsm.dcs.entity.user;

import org.springframework.data.repository.CrudRepository;

public interface UserAuthCodeLimitRepository extends CrudRepository<UserAuthCodeLimit, String> {
}
