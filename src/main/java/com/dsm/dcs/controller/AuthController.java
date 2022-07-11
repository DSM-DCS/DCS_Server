package com.dsm.dcs.controller;

import com.dsm.dcs.dto.request.LoginRequest;
import com.dsm.dcs.dto.request.PhoneNumberRequest;
import com.dsm.dcs.dto.request.SmsRequest;
import com.dsm.dcs.dto.request.UserSignUpRequest;
import com.dsm.dcs.dto.request.VerificationPasswordRequest;
import com.dsm.dcs.dto.TokenDto;
import com.dsm.dcs.dto.response.RoleResponse;
import com.dsm.dcs.service.auth.AuthService;
import com.dsm.dcs.service.auth.CheckExistsService;
import com.dsm.dcs.service.auth.SmsService;
import com.dsm.dcs.service.auth.VerificationPasswordService;
import com.dsm.dcs.service.auth.TokenRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Validated
@RestController
public class AuthController {

    private final TokenRefreshTokenService tokenRefreshTokenService;
    private final CheckExistsService checkExistsService;
    private final VerificationPasswordService verificationPasswordService;
    private final AuthService authService;
    private final SmsService smsService;

    @PostMapping("/sign-in")
    public TokenDto SignIn(@RequestBody @Valid LoginRequest request) {
        return authService.signIn(request);
    }

    @PostMapping("sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenDto SignUp(@RequestBody @Valid UserSignUpRequest request) {
        return authService.signUp(request);
    }

    @DeleteMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout() {
        authService.logout();
    }

    @PatchMapping("/token")
    public TokenDto TokenRefresh(@RequestBody @Valid TokenDto tokenDto) {
        return tokenRefreshTokenService.execute(tokenDto);
    }

    @PostMapping("/sms/send")
    public void sendMessageAuthCode(@RequestBody @Valid PhoneNumberRequest request) {
        smsService.sendMessageAuthCode(request);
    }

    @PostMapping("/sms/check")
    public void checkCoincideAuthCode(@RequestBody @Valid SmsRequest request) {
        smsService.checkCoincideAuthCode(request);
    }

    @RequestMapping(value = "/account-id", method = RequestMethod.HEAD)
    public void CheckUserExists(@NotBlank @RequestParam(name = "accountId") String accountId) {
        checkExistsService.checkAccountIdExists(accountId);
    }

    @RequestMapping(value = "/phone-number", method = RequestMethod.HEAD)
    public void CheckPhoneNumberExists(@NotBlank @RequestParam(name = "phoneNumber") String phoneNumber) {
        checkExistsService.checkPhoneNumberExists(phoneNumber);
    }

    @PostMapping("/verification-password")
    public void verificationCurrentPassword(@RequestBody @Valid VerificationPasswordRequest request) {
        verificationPasswordService.execute(request);
    }

    @GetMapping("/role")
    public RoleResponse getAccountRole() {
        return authService.getAccountRole();
    }

}