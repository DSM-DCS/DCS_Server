package com.dsm.dcs.controller;

import com.dsm.dcs.dto.request.UserLoginRequest;
import com.dsm.dcs.dto.request.VerificationPasswordRequest;
import com.dsm.dcs.dto.response.UserRefreshTokenResponse;
import com.dsm.dcs.dto.response.UserTokenResponse;
import com.dsm.dcs.service.auth.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@RestController
public class AuthController {

    private final UserSignInService userSignInService;
    private final TokenRefreshTokenService tokenRefreshTokenService;
    private final CheckUserExistsService checkUserExistsService;
    private final CheckStudentNumberExistsService checkStudentNumberExistsService;
    private final CheckEmailExistsService checkEmailExistsService;
    private final CheckPhoneNumberExistsService checkPhoneNumberExistsService;
    private final VerificationPasswordService verificationPasswordService;

    @PostMapping("/token")
    public UserTokenResponse userSignIn(@RequestBody @Valid UserLoginRequest request) {
        return userSignInService.execute(request);
    }

    @PatchMapping("/token")
    public UserRefreshTokenResponse userTokenRefresh(@RequestHeader("Refresh-Token") String refreshToken) {
        return tokenRefreshTokenService.execute(refreshToken);
    }

    @RequestMapping(value = "/account-id", method = RequestMethod.HEAD)
    public void CheckUserExists(@NotBlank @RequestParam(name = "accountId") String accountId) {
        checkUserExistsService.execute(accountId);
    }

    @RequestMapping(value = "/email", method = RequestMethod.HEAD)
    public void CheckEmailExists(@NotBlank @RequestParam(name = "email") String email) {
        checkEmailExistsService.execute(email);
    }

    @RequestMapping(value = "/student-number", method = RequestMethod.HEAD)
    public void CheckStudentNumberExists(@NotBlank @RequestParam(name = "studentNumber") Integer studentNumber) {
        checkStudentNumberExistsService.execute(studentNumber);
    }

    @RequestMapping(value = "/phone-number", method = RequestMethod.HEAD)
    public void CheckPhoneNumberExists(@NotBlank @RequestParam(name = "phoneNumber") String phoneNumber) {
        checkPhoneNumberExistsService.execute(phoneNumber);
    }

    @PostMapping("/verification-password")
    public void verificationCurrentPassword(@RequestBody @Valid VerificationPasswordRequest request) {
        verificationPasswordService.execute(request);
    }

}