package com.dsm.dcs.controller;

import com.dsm.dcs.dto.request.CourierSignUpRequest;
import com.dsm.dcs.dto.response.UserTokenResponse;
import com.dsm.dcs.service.courier.CourierSignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/couriers")
@RequiredArgsConstructor
@Validated
@RestController
public class CourierController {

    private final CourierSignUpService courierSignUpService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserTokenResponse courierSignUp(@RequestBody @Valid CourierSignUpRequest request) {
        return courierSignUpService.execute(request);
    }
}
