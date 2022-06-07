package com.dsm.dcs.entity.courier;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourierRepository extends CrudRepository<Courier, Long> {

    Optional<Courier> findByAccountId(String accountId);
}
