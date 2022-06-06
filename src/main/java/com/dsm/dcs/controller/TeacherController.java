package com.dsm.dcs.controller;

import com.dsm.dcs.dto.request.TeacherSignUpRequest;
import com.dsm.dcs.dto.response.UserTokenResponse;
import com.dsm.dcs.service.teacher.TeacherSignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/teachers")
@RestController
public class TeacherController {

    private final TeacherSignUpService teacherSignUpService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserTokenResponse teacherSignUp(@RequestBody @Valid TeacherSignUpRequest request) {
        return teacherSignUpService.execute(request);
    }
}
