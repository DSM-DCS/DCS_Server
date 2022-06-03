package com.dsm.dcs.entity.delivery;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeliveryRepository extends CrudRepository<Delivery, Long> {
    List<Delivery> findAllByOrderByIdDesc(Pageable page);
    List<Delivery> findByUserOrderByCreatedDateDesc(Pageable page);
}
