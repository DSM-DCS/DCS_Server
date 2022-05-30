package com.dsm.dcs.controller;

import com.dsm.dcs.dto.request.SendEmailRequest;
import com.dsm.dcs.dto.request.UpdatePasswordRequest;
import com.dsm.dcs.dto.request.UserSignUpRequest;
import com.dsm.dcs.dto.request.VerificationAuthCodeRequest;
import com.dsm.dcs.dto.response.UserAccountIdResponse;
import com.dsm.dcs.dto.response.UserListResponse;
import com.dsm.dcs.dto.response.UserTokenResponse;
import com.dsm.dcs.service.user.SearchAccountIdService;
import com.dsm.dcs.service.user.UpdatePasswordService;
import com.dsm.dcs.service.user.UserSignUpService;
import com.dsm.dcs.service.user.UserService;
import com.dsm.dcs.service.user.SendEmailAuthCodeService;
import com.dsm.dcs.service.user.VerificationAuthCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;

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
    public UserListResponse searchUser(@RequestParam(value = "name") String name) {
        return userService.searchUser(name);
    }

    @GetMapping
    public UserListResponse getUser() {
        return userService.getUser();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserTokenResponse userSignUp(@RequestBody @Valid UserSignUpRequest request) {
        return userSignUpService.execute(request);
    }

    @PostMapping("/email-verifications")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendEmailAuthCode(@RequestBody @Valid SendEmailRequest request) {
        sendEmailAuthCodeService.execute(request);
    }

    @PatchMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
