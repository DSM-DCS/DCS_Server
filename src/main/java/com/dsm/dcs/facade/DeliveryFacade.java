package com.dsm.dcs.facade;

import com.dsm.dcs.dto.response.DeliveryListResponse;
import com.dsm.dcs.dto.response.DeliveryMainListResponse;
import com.dsm.dcs.dto.response.DeliveryNotUserListResponse;
import com.dsm.dcs.entity.account.Account;
import com.dsm.dcs.entity.delivery.Delivery;
import com.dsm.dcs.entity.delivery.DeliveryRepository;
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

    public DeliveryListResponse getDeliveryList(Account account, Pageable page) {
        return new DeliveryListResponse(deliveryRepository.findAllByAccountAndIsReceiptFalseOrderByCreatedDateDesc(account, page)
                .stream().map(this::getDelivery).collect(Collectors.toList()));
    }

    public DeliveryListResponse getReceivedDeliveryList(Account account, Pageable page) {
        return new DeliveryListResponse(deliveryRepository.findAllByAccountAndIsReceiptTrueOrderByCreatedDateDesc(account, page)
                .stream().map(this::getDelivery).collect(Collectors.toList()));
    }

    public DeliveryListResponse getDeliveryList(Pageable page) {
        return new DeliveryListResponse(deliveryRepository.findAllByAccountNotNullAndIsReceiptFalseOrderByCreatedDateDesc(page)
                .stream().map(this::getDelivery).collect(Collectors.toList()));
    }

    public DeliveryNotUserListResponse getNotUserDeliveryList(Pageable page) {
        return new DeliveryNotUserListResponse(deliveryRepository.findAllByAccountNullAndIsReceiptFalseOrderByCreatedDateDesc(page)
                .stream().map(this::getNotUserDelivery).collect(Collectors.toList()));
    }

    public DeliveryMainListResponse getMainDeliveryList(Pageable page) {
        return new DeliveryMainListResponse(deliveryRepository.findAllByIsReceiptFalseOrderByCreatedDateDesc(page)
                .stream().map(this::getMainDelivery).collect(Collectors.toList()));
    }

    private DeliveryListResponse.DeliveryResponse getDelivery(Delivery delivery) {
        return DeliveryListResponse.DeliveryResponse.builder()
                .id(delivery.getId())
                .courierCompany(delivery.getCourierCompany().name())
                .name(delivery.getAccount().getName())
                .phoneNumber(delivery.getPhoneNumber())
                .products(delivery.getProducts())
                .createdDate(delivery.getCreatedDate())
                .build();
    }

    private DeliveryNotUserListResponse.DeliveryNullUserResponse getNotUserDelivery(Delivery delivery) {
        return DeliveryNotUserListResponse.DeliveryNullUserResponse.builder()
                .id(delivery.getId())
                .courierCompany(delivery.getCourierCompany().name())
                .phoneNumber(delivery.getPhoneNumber())
                .products(delivery.getProducts())
                .createdDate(delivery.getCreatedDate())
                .build();
    }

    private DeliveryMainListResponse.DeliveryResponse getMainDelivery(Delivery delivery) {
        return DeliveryMainListResponse.DeliveryResponse.builder()
                .id(delivery.getId())
                .courierCompany(delivery.getCourierCompany().name())
                .products(delivery.getProducts())
                .createdDate(delivery.getCreatedDate())
                .build();
    }

}