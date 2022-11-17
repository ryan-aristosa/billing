package com.academy.billing.service;

import com.academy.billing.exception.BillingNotFoundException;
import com.academy.billing.model.Billing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BillingService {

    Billing saveBilling(Billing billing);

    Page<Billing> findAllBillings(Pageable pageable);

    Billing findBillingById(Long id) throws BillingNotFoundException;

    Billing updateBilling(Long id, Billing newBilling) throws BillingNotFoundException;

    Long  deleteBilling(Long id) throws BillingNotFoundException;

}
