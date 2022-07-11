package com.dsm.dcs.controller;

import com.dsm.dcs.dto.request.PasswordRequest;
import com.dsm.dcs.dto.request.PhoneNumberRequest;
import com.dsm.dcs.dto.response.UserListResponse;
import com.dsm.dcs.dto.response.UserResponse;
import com.dsm.dcs.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/search")
    public UserListResponse searchUser(@RequestParam(value = "name") String name, Pageable page) {
        return userService.searchUser(name, page);
    }

    @GetMapping
    public UserListResponse getUserList(Pageable page) {
        return userService.getUser(page);
    }

    @GetMapping("/mypage")
    public UserResponse getMyPage() {
        return userService.getUser();
    }


    @PatchMapping("/phone-number")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePhoneNumber(@RequestBody @Valid PhoneNumberRequest request) {
        userService.changePhoneNumber(request);
    }

    @PatchMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@RequestBody @Valid PasswordRequest request) {
        userService.changePassword(request);
    }

}
