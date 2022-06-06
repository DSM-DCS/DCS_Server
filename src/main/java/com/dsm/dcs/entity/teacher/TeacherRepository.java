package com.dsm.dcs.entity.teacher;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    Optional<Teacher> findByAccountId(String accountId);
}
