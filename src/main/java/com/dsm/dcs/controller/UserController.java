package com.dsm.dcs.controller;

import com.dsm.dcs.dto.request.SendEmailRequest;
import com.dsm.dcs.dto.request.UpdatePasswordRequest;
import com.dsm.dcs.dto.request.UserSignUpRequest;
import com.dsm.dcs.dto.request.VerificationAuthCodeRequest;
import com.dsm.dcs.dto.response.UserAccountIdResponse;
import com.dsm.dcs.dto.response.UserListResponse;
import com.dsm.dcs.dto.response.UserTokenResponse;
import com.dsm.dcs.service.user.*;
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
    private final SendEmailAuthCodeService sendEmailAuthCodeService;
    private final SearchAccountIdService searchAccountIdService;
    private final UpdatePasswordService updatePasswordService;
    private final VerificationAuthCodeService verificationAuthCodeService;

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/email-verifications")
    public void sendEmailAuthCode(@RequestBody @Valid SendEmailRequest request) {
        sendEmailAuthCodeService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    public void updatePassword(@RequestBody @Valid UpdatePasswordRequest request) {
        updatePasswordService.execute(request);
    }

    @GetMapping("/accounts/{email}")
    public UserAccountIdResponse searchAccountId(@PathVariable(name = "email") String email) {
        return searchAccountIdService.execute(email);
    }

    @PutMapping("/email-verifications")
    public void verifyEmail(@RequestBody @Valid VerificationAuthCodeRequest request) {
        verificationAuthCodeService.execute(request);
    }

}
