package com.dsm.dcs.entity.delivery;

import com.dsm.dcs.entity.account.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeliveryRepository extends CrudRepository<Delivery, Long> {
    List<Delivery> findAllByAccountNotNullAndIsReceiptFalseOrderByCreatedDateDesc(Pageable page);
    List<Delivery> findAllByAccountAndIsReceiptFalseOrderByCreatedDateDesc(Account account, Pageable page);
    List<Delivery> findAllByAccountAndIsReceiptTrueOrderByCreatedDateDesc(Account account, Pageable page);
    List<Delivery> findAllByAccountNullAndIsReceiptFalseOrderByCreatedDateDesc(Pageable page);
    List<Delivery> findAllByIsReceiptFalseOrderByCreatedDateDesc(Pageable page);
}
