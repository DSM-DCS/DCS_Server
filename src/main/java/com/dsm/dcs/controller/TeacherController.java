package com.dsm.dcs.controller;

import com.dsm.dcs.dto.request.TeacherSignUpRequest;
import com.dsm.dcs.dto.response.UserTokenResponse;
import com.dsm.dcs.service.teacher.TeacherSignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
