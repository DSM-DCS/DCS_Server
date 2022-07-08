package com.dsm.dcs.facade;

import com.dsm.dcs.dto.response.UserListResponse;
import com.dsm.dcs.entity.user.User;
import com.dsm.dcs.entity.user.UserAuthCode;
import com.dsm.dcs.entity.user.UserAuthCodeRepository;
import com.dsm.dcs.entity.user.UserRepository;
import com.dsm.dcs.exception.ForbiddenException;
import com.dsm.dcs.exception.InvalidAuthCodeException;
import com.dsm.dcs.exception.InvalidJwtException;
import com.dsm.dcs.exception.UserNotFoundException;
import com.dsm.dcs.exception.AuthCodeAlreadyVerifiedException;
import com.dsm.dcs.exception.UnVerifiedAuthCodeException;
import com.dsm.dcs.exception.AccountIdExistsException;
import com.dsm.dcs.exception.StudentNumberExistsException;
import com.dsm.dcs.exception.PhoneNumberExistsException;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;
    private final UserAuthCodeRepository userAuthCodeRepository;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw InvalidJwtException.EXCEPTION;
        }

        return getUserByAccountId(authentication.getName());
    }

    public Boolean getRoleBoolean() {
        if(getCurrentUser().getRole().name() != "ROLE_USER"){
            return false;
        }
        return true;
    }

    public void getRole() {
        if(!getRoleBoolean()){
            throw ForbiddenException.EXCEPTION;
        }
    }

    public UserListResponse getUserList(Pageable page) {
        return new UserListResponse(userRepository.findAllByOrderByIdDesc(page).stream()
                .map(this::getUser).collect(Collectors.toList()));
    }

    public User getUserByAccountId(String accountId) {
        return userRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElse(null);
    }

    public UserListResponse getUserByName(String name, Pageable page) {
        return new UserListResponse(userRepository.findAllByNameContaining(name, page).stream()
                .map(this::getUser).collect(Collectors.toList()));
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void checkUserExists(String accountId) {
        if (userRepository.findByAccountId(accountId).isPresent()) {
            throw AccountIdExistsException.EXCEPTION;
        }
    }

    public void checkPhoneNumberExists(String phoneNumber) {
        if (userRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            throw PhoneNumberExistsException.EXCEPTION;
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

    private UserListResponse.UserResponse getUser(User user) {
        return new UserListResponse.UserResponse(user.getName());
    }

}
