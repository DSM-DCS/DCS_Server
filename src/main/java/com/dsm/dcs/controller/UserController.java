package com.dsm.dcs.controller;

import com.dsm.dcs.dto.request.UserSignUpRequest;
import com.dsm.dcs.dto.response.UserListResponse;
import com.dsm.dcs.dto.response.UserTokenResponse;
import com.dsm.dcs.service.user.UserService;
import com.dsm.dcs.service.user.UserSignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@RestController
public class UserController {

    private final UserSignUpService userSignUpService;
    private final UserService userService;

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
