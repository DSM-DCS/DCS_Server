package com.dsm.dcs.facade;

import com.dsm.dcs.dto.response.DeliveryListResponse;
import com.dsm.dcs.dto.response.DeliveryNullUserListResponse;
import com.dsm.dcs.entity.delivery.Delivery;
import com.dsm.dcs.entity.delivery.DeliveryRepository;
import com.dsm.dcs.entity.user.User;
import com.dsm.dcs.exception.DeliveryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DeliveryFacade {

    private final DeliveryRepository deliveryRepository;

    public Delivery getDeliveryById(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> DeliveryNotFoundException.EXCEPTION);
    }

    public void deleteDelivery(Long id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> DeliveryNotFoundException.EXCEPTION);
        deliveryRepository.delete(delivery);
    }

    public DeliveryListResponse getDeliveryList(User user, Pageable page) {
        return new DeliveryListResponse(deliveryRepository.findAllByUserOrderByCreatedDateDesc(user, page)
                .stream().map(this::getDelivery).collect(Collectors.toList()));
    }

    public DeliveryListResponse getDeliveryUserNotNullList(Pageable page) {
        return new DeliveryListResponse(deliveryRepository.findAllByUserNotNullOrderByCreatedDateDesc(page)
                .stream().map(this::getDelivery).collect(Collectors.toList()));
    }

    public DeliveryNullUserListResponse getDeliveryUserNullList(Pageable page) {
        return new DeliveryNullUserListResponse(deliveryRepository.findAllByUserOrderByCreatedDateDesc(null, page)
                .stream().map(this::getUserNullDeilvery).collect(Collectors.toList()));
    }

    private DeliveryListResponse.DeliveryResponse getDelivery(Delivery delivery) {
        return DeliveryListResponse.DeliveryResponse.builder()
                .courierCompany(delivery.getCourierCompany().name())
                .name(delivery.getUser().getName())
                .createdDate(delivery.getCreatedDate())
                .build();
    }

    private DeliveryNullUserListResponse.DeliveryNullUserResponse getUserNullDeilvery(Delivery delivery) {
        return DeliveryNullUserListResponse.DeliveryNullUserResponse.builder()
                .courierCompany(delivery.getCourierCompany().name())
                .phoneNumber(delivery.getPhoneNumber())
                .createdDate(delivery.getCreatedDate())
                .build();
    }

}