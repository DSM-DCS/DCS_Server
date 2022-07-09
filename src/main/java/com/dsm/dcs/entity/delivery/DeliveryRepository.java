package com.dsm.dcs.entity.delivery;

import com.dsm.dcs.entity.account.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeliveryRepository extends CrudRepository<Delivery, Long> {
    List<Delivery> findAllByUserNotNullOrderByCreatedDateDesc(Pageable page);
    List<Delivery> findAllByUserOrderByCreatedDateDesc(Account account, Pageable page);

}
