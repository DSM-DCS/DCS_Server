package com.dsm.dcs.controller;

import com.dsm.dcs.dto.request.VerificationPasswordRequest;
import com.dsm.dcs.dto.TokenDto;
import com.dsm.dcs.service.auth.CheckExistsService;
import com.dsm.dcs.service.auth.VerificationPasswordService;
import com.dsm.dcs.service.auth.TokenRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@RestController
public class AuthController {

    private final TokenRefreshTokenService tokenRefreshTokenService;
    private final CheckExistsService checkExistsService;
    private final VerificationPasswordService verificationPasswordService;

    @PatchMapping("/token")
    public TokenDto TokenRefresh(@RequestBody @Valid TokenDto tokenDto) {
        return tokenRefreshTokenService.execute(tokenDto);
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

}