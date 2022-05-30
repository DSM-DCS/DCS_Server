package com.dsm.dcs.controller;

import com.dsm.dcs.dto.request.UserSignUpRequest;
import com.dsm.dcs.dto.response.UserListResponse;
import com.dsm.dcs.dto.response.UserTokenResponse;
import com.dsm.dcs.service.user.UserService;
import com.dsm.dcs.service.user.UserSignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@RestController
public class UserController {

    private final UserSignUpService userSignUpService;
    private final UserService userService;

    @GetMapping("/search")
    @ResponseStatus
    public UserListResponse searchUser(@RequestParam(value = "name") String name) {
        return userService.searchUser(name);
    }

    @GetMapping
    @ResponseStatus
    public UserListResponse getUser() {
        return userService.getUser();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserTokenResponse userSignUp(@RequestBody @Valid UserSignUpRequest request) {
        return userSignUpService.execute(request);
    }

}
