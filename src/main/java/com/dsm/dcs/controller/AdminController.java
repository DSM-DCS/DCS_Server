package com.dsm.dcs.controller;

import com.dsm.dcs.dto.TokenDto;
import com.dsm.dcs.dto.request.LoginRequest;
import com.dsm.dcs.service.admin.AdminAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequestMapping("/admin")
@RequiredArgsConstructor
@Validated
@RestController
public class AdminController {

    private final AdminAuthService adminAuthService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TokenDto courierSignUp(@RequestBody @Valid LoginRequest request) {
        return adminAuthService.adminSignIn(request);
    }
}
