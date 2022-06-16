package com.dsm.dcs.controller;

import com.dsm.dcs.dto.request.DeliveryListRequest;
import com.dsm.dcs.dto.response.DeliveryIdListResponse;
import com.dsm.dcs.dto.response.DeliveryListResponse;
import com.dsm.dcs.dto.response.DeliveryNullUserListResponse;
import com.dsm.dcs.dto.response.DeliveryResponse;
import com.dsm.dcs.service.delivery.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    private final DeliveryService deliveryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveDelivery(@Valid @RequestBody DeliveryListRequest request) {
        deliveryService.saveDelivery(request);
    }

    @PatchMapping("/{deliveryId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public DeliveryIdListResponse.DeliveryIdResponse updateDeliveryUser(@PathVariable("deliveryId") Long deliveryId,
                                                                        @PathVariable("userId") Long userId) {
        return deliveryService.updateDeliveryUser(deliveryId, userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDelivery(@PathVariable("id") Long deliveryId) {
        deliveryService.deleteDelivery(deliveryId);
    }

    @GetMapping
    public DeliveryListResponse getDeliveryList(Pageable page) {
        return deliveryService.getDeliveryList(page);
    }

    @GetMapping("/null/user")
    public DeliveryNullUserListResponse getDeliveryUserNullList(Pageable page) {
        return deliveryService.getDeliveryUserNullList(page);
    }

    @GetMapping("/user")
    public DeliveryListResponse myDeliveryList(Pageable page) {
        return deliveryService.getMyDeliveryList(page);
    }

    @GetMapping("/{delivery_id}")
    public DeliveryListResponse.DeliveryResponse getDelivery(@PathVariable("delivery_id") Long deliveryId) {
        return deliveryService.getDelivery(deliveryId);
    }

}
