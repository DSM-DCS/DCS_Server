package com.dsm.dcs.controller;

import com.dsm.dcs.dto.request.VerificationPasswordRequest;
import com.dsm.dcs.dto.TokenDto;
import com.dsm.dcs.service.auth.CheckPhoneNumberExistsService;
import com.dsm.dcs.service.auth.VerificationPasswordService;
import com.dsm.dcs.service.auth.CheckEmailExistsService;
import com.dsm.dcs.service.auth.CheckStudentNumberExistsService;
import com.dsm.dcs.service.auth.TokenRefreshTokenService;
import com.dsm.dcs.service.auth.CheckUserExistsService;
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
    private final CheckUserExistsService checkUserExistsService;
    private final CheckStudentNumberExistsService checkStudentNumberExistsService;
    private final CheckEmailExistsService checkEmailExistsService;
    private final CheckPhoneNumberExistsService checkPhoneNumberExistsService;
    private final VerificationPasswordService verificationPasswordService;

    @PatchMapping("/token")
    public TokenDto userTokenRefresh(@RequestBody @Valid TokenDto tokenDto) {
        return tokenRefreshTokenService.execute(tokenDto);
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