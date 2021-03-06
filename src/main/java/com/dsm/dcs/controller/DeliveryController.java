package com.dsm.dcs.controller;

import com.dsm.dcs.dto.request.DeliveryListRequest;
import com.dsm.dcs.dto.response.DeliveryIdListResponse;
import com.dsm.dcs.dto.response.DeliveryListResponse;
import com.dsm.dcs.dto.response.DeliveryMainListResponse;
import com.dsm.dcs.dto.response.DeliveryNotUserListResponse;
import com.dsm.dcs.service.delivery.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    public DeliveryIdListResponse.DeliveryIdResponse updateDeliveryUser(@PathVariable("deliveryId") Long deliveryId,
                                                                        @PathVariable("userId") Long userId) {
        return deliveryService.updateDeliveryUser(userId, deliveryId);
    }

    @PatchMapping("receipt/{deliveryId}")
    public DeliveryIdListResponse.DeliveryIdResponse receiptDelivery(@PathVariable("deliveryId") Long deliveryId) {
        return deliveryService.receiptDelivery(deliveryId);
    }

    @GetMapping
    public DeliveryListResponse getDeliveryList(Pageable page) {
        return deliveryService.getDeliveryList(page);
    }

    @GetMapping("/not-user")
    public DeliveryNotUserListResponse getNotUserDeliveryList(Pageable page) {
        return deliveryService.getNotUserDeliveryList(page);
    }

    @GetMapping("/user")
    public DeliveryListResponse myDeliveryList(Pageable page) {
        return deliveryService.getMyDeliveryList(page);
    }

    @GetMapping("/received/user")
    public DeliveryListResponse receivedDeliveryList(Pageable page) {
        return deliveryService.getReceivedDeliveryList(page);
    }

    @GetMapping("/main")
    public DeliveryMainListResponse getMainDeliveryList(Pageable page) {
        return deliveryService.getMainDeliveryList(page);
    }

}
