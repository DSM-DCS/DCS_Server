package com.dsm.dcs.controller;

import com.dsm.dcs.dto.request.DeliveryListRequest;
import com.dsm.dcs.dto.response.DeliveryIdListResponse;
import com.dsm.dcs.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/delivery")
@RestController
public class CourierController {

    private final CourierService courierService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryIdListResponse seveDelivery(@Valid @RequestBody DeliveryListRequest request) {
        return courierService.seveDelivery(request);
    }

}
