package com.academy.billing.service;

import com.academy.billing.exception.RecordNotFoundException;
import com.academy.billing.model.Billing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BillingService {

    Billing saveBilling(Billing billing);

    Page<Billing> findAllBillings(Pageable pageable);

    Billing findBillingById(Long id) throws RecordNotFoundException;

    Billing updateBilling(Long id, Billing newBilling) throws RecordNotFoundException ;

    Long  deleteBilling(Long id) throws RecordNotFoundException;

}
