package com.dsm.dcs.facade;

import com.dsm.dcs.entity.delivery.Delivery;
import com.dsm.dcs.entity.delivery.DeliveryRepository;
import com.dsm.dcs.entity.user.User;
import com.dsm.dcs.exception.DeliveryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<Delivery> getDeliveryList(Pageable page) {
        return deliveryRepository.findAllByOrderByIdDesc(page);
    }

    public List<Delivery> getDeliveryList(User user, Pageable page) {
        return deliveryRepository.findByUserOrderByCreatedDateDesc(user, page);
    }

}
