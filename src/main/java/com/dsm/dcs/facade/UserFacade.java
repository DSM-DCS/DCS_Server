package com.dsm.dcs.facade;

import com.dsm.dcs.entity.user.User;
import com.dsm.dcs.entity.user.UserRepository;
import com.dsm.dcs.exception.AccountIdAlreadyExistsException;
import com.dsm.dcs.exception.EmailAlreadyExistsException;
import com.dsm.dcs.exception.StudentNumberAlreadyExistsException;
import com.dsm.dcs.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByAccountId(accountId);
    }

    public List<User> getUsetList() {
        return userRepository.findAllByOrderByStudentNumberDesc();
    }

    public User getUserByAccountId(String id) {
        return userRepository.findByAccountId(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElse(null);
    }

    public List<User> getUserByName(String name) {
        return userRepository.findAllByNameContaining(name);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void checkUserExists(String accountId) {
        if (userRepository.findByAccountId(accountId).isPresent()) {
            throw AccountIdAlreadyExistsException.EXCEPTION;
        }
    }

    public void checkStudentNumberExists(Integer studentNumber) {
        if (userRepository.findByStudentNumber(studentNumber).isPresent()) {
            throw StudentNumberAlreadyExistsException.EXCEPTION;
        }
    }

    public void checkEmailExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw EmailAlreadyExistsException.EXCEPTION;
        }
    }
}
