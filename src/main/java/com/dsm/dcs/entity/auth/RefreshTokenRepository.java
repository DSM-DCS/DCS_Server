package com.dsm.dcs.entity.auth;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

    Optional<RefreshToken> findById(String accountId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
