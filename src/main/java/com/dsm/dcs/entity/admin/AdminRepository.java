package com.dsm.dcs.entity.admin;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin, Long> {
    Optional<Admin> findByAdminId(String adminId);
}
