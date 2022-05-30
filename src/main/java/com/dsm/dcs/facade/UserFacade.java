package com.dsm.dcs.facade;

import com.dsm.dcs.entity.user.User;
import com.dsm.dcs.entity.user.UserAuthCode;
import com.dsm.dcs.entity.user.UserAuthCodeRepository;
import com.dsm.dcs.entity.user.UserRepository;
import com.dsm.dcs.exception.InvalidAuthCodeException;
import com.dsm.dcs.exception.UserNotFoundException;
import com.dsm.dcs.exception.UserAlreadyExistsException;
import com.dsm.dcs.exception.AuthCodeAlreadyVerifiedException;
import com.dsm.dcs.exception.EmailAlreadyExistsException;
import com.dsm.dcs.exception.UnVerifiedAuthCodeException;
import com.dsm.dcs.exception.AccountIdAlreadyExistsException;
import com.dsm.dcs.exception.StudentNumberAlreadyExistsException;
import com.dsm.dcs.exception.PhoneNumberAlreadyExistsException;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;
    private final UserAuthCodeRepository userAuthCodeRepository;

    public User getCurrentUser() {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByAccountId(accountId);
    }

    public List<User> getUserList() {
        return userRepository.findAllByOrderByStudentNumberDesc();
    }

    public User getUserByAccountId(String accountId) {
        return userRepository.findByAccountId(accountId)
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

    public boolean isAlreadyExists(String email) {
        if(userRepository.findByEmail(email).isPresent()) {
            throw UserAlreadyExistsException.EXCEPTION;
        }
        return true;
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

    public void checkPhoneNumberExists(String phoneNumber) {
        if (userRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            throw PhoneNumberAlreadyExistsException.EXCEPTION;
        }
    }

    public boolean checkVerified(boolean isVerified) {
        if (!isVerified) {
            throw UnVerifiedAuthCodeException.EXCEPTION;
        }

        return true;
    }

    public boolean isVerified(String email) {
        return getAuthCodeById(email).isVerify();
    }

    public UserAuthCode getAuthCodeById(String email) {
        return userAuthCodeRepository.findById(email)
                .orElseThrow(() -> InvalidAuthCodeException.EXCEPTION);
    }

    public boolean isAlreadyVerified(boolean isVerified) {
        if (isVerified) {
            throw AuthCodeAlreadyVerifiedException.EXCEPTION;
        }

        return true;
    }

    public boolean compareCode(String reqCode, String code) {
        return reqCode.equals(code);
    }

}
