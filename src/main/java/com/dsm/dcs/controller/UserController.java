package com.dsm.dcs.controller;

import com.dsm.dcs.dto.TokenDto;
import com.dsm.dcs.dto.request.LoginRequest;
import com.dsm.dcs.dto.request.SendEmailRequest;
import com.dsm.dcs.dto.request.UpdatePasswordRequest;
import com.dsm.dcs.dto.request.UserSignUpRequest;
import com.dsm.dcs.dto.request.ChangePasswordRequest;
import com.dsm.dcs.dto.request.VerificationAuthCodeRequest;
import com.dsm.dcs.dto.response.UserListResponse;
import com.dsm.dcs.dto.response.UserResponse;
import com.dsm.dcs.service.user.UserAuthService;
import com.dsm.dcs.service.user.UserService;
import com.dsm.dcs.service.user.SendIdAuthCodeService;
import com.dsm.dcs.service.user.SendPasswordAuthCodeService;
import com.dsm.dcs.service.user.ChangePasswordService;
import com.dsm.dcs.service.user.SendEmailAuthCodeService;
import com.dsm.dcs.service.user.UpdatePasswordService;
import com.dsm.dcs.service.user.VerificationAuthCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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

import javax.validation.Valid;

@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@RestController
public class UserController {

    private final UserAuthService userAuthService;
    private final UserService userService;
    private final SendEmailAuthCodeService sendEmailAuthCodeService;
    private final UpdatePasswordService updatePasswordService;
    private final VerificationAuthCodeService verificationAuthCodeService;
    private final SendPasswordAuthCodeService sendPasswordAuthCodeService;
    private final SendIdAuthCodeService sendIdAuthCodeService;
    private final ChangePasswordService changePasswordService;

    @PostMapping("/token")
    public TokenDto userSignIn(@RequestBody @Valid LoginRequest request) {
        return userAuthService.signIn(request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TokenDto userSignUp(@RequestBody @Valid UserSignUpRequest request) {
        return userAuthService.signUp(request);
    }

    @GetMapping("/search")
    public UserListResponse searchUser(@RequestParam(value = "name") String name, Pageable page) {
        return userService.searchUser(name, page);
    }

    @GetMapping
    public UserListResponse getUserList(Pageable page) {
        return userService.getUser(page);
    }

    @PostMapping("/email-verifications")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendEmailAuthCode(@RequestBody @Valid SendEmailRequest request) {
        sendEmailAuthCodeService.execute(request);
    }

    @PostMapping("/email-verifications/password")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendPasswordAuthCode(@RequestBody @Valid SendEmailRequest request) {
        sendPasswordAuthCodeService.execute(request);
    }

    @PostMapping("/email-verifications/id")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendIdAuthCode(@RequestBody @Valid SendEmailRequest request) {
        sendIdAuthCodeService.execute(request);
    }

    @PatchMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@RequestBody @Valid UpdatePasswordRequest request) {
        updatePasswordService.execute(request);
    }

    @PatchMapping("/email-verifications/newpassword")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@RequestBody ChangePasswordRequest request) {
        changePasswordService.execute(request);
    }

    @PutMapping("/email-verifications")
    public void verifyEmail(@RequestBody @Valid VerificationAuthCodeRequest request) {
        verificationAuthCodeService.execute(request);
    }

    @GetMapping("/mypage")
    public UserResponse getMyPage() {
        return userService.getUser();
    }

}
