package com.dsm.dcs.entity.delivery;

import com.dsm.dcs.entity.account.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeliveryRepository extends CrudRepository<Delivery, Long> {
    List<Delivery> findAllByAccountNotNullOrderByCreatedDateDesc(Pageable page);
    List<Delivery> findAllByAccountOrderByCreatedDateDesc(Account account, Pageable page);
    List<Delivery> findAllByAccountNullOrderByCreatedDateDesc(Pageable page);

}
