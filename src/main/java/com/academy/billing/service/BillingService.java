package com.academy.billing.service;

import com.academy.billing.model.Billing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BillingService {

    Page<Billing> findAllAccounts(Pageable pageable);
    Billing findAccountById(Long id);
    Billing saveBilling(Billing saveBilling);
    Billing updateBilling(Long id, Billing billing);
    void deleteBilling(Long id);

}
