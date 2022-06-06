package com.dsm.dcs.facade;

import com.dsm.dcs.entity.teacher.Teacher;
import com.dsm.dcs.entity.teacher.TeacherRepository;
import com.dsm.dcs.exception.AccountIdAlreadyExistsException;
import com.dsm.dcs.exception.TeacherNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherFacade {

    private final TeacherRepository teacherRepository;

    public Teacher getUserByAccountId(String accountId) {
        return teacherRepository.findByAccountId(accountId)
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);
    }

    public void checkUserExists(String accountId) {
        if (teacherRepository.findByAccountId(accountId).isPresent()) {
            throw AccountIdAlreadyExistsException.EXCEPTION;
        }
    }

}
