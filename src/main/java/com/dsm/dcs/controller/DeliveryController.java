package com.dsm.dcs.controller;

import com.dsm.dcs.dto.request.DeliveryListRequest;
import com.dsm.dcs.dto.response.DeliveryIdListResponse;
import com.dsm.dcs.service.CourierService;
import com.dsm.dcs.service.teacher.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/delivery")
@RestController
public class DeliveryController {

    private final CourierService courierService;
    private final TeacherService teacherService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryIdListResponse seveDelivery(@Valid @RequestBody DeliveryListRequest request) {
        return courierService.saveDelivery(request);
    }

    @PatchMapping("/{delivery_id}/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public DeliveryIdListResponse.DeliveryIdResponse updateDeliveryUser(@PathVariable("delivery_id") Long deliveryId,
                                                                        @PathVariable("user_id") Long userId) {
        return teacherService.updateUser(deliveryId, userId);
    }

    @DeleteMapping("/{delivery_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDelivery(@PathVariable("delivery_id") Long deliveryId) {
        teacherService.deleteDelivery(deliveryId);
    }

}
