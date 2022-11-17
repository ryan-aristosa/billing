package com.academy.billing.service;

import com.academy.billing.exception.BillingNotFoundException;
import com.academy.billing.model.Billing;
import com.academy.billing.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    BillingRepository billingRepository;


    @Override
    public Billing saveBilling(Billing billing) {
        return billingRepository.save(billing);
    }

    @Override
    public Page<Billing> findAllBillings(Pageable pageable) {
        return billingRepository.findAll(pageable);
    }

    @Override
    public Billing findBillingById(Long id) throws BillingNotFoundException {
        return billingRepository.findById(id).orElseThrow(() -> new BillingNotFoundException("Billing"));
    }

    @Override
    public Billing updateBilling(Long id, Billing newBilling) throws BillingNotFoundException {
        return billingRepository.findById(id).map(billing -> {
            billing.setAmount(newBilling.getAmount());
            billing.setType(newBilling.getType());
            return billingRepository.save(billing);
        }).orElseThrow(() -> new BillingNotFoundException("Billing"));
    }

    @Override
    public Long deleteBilling(Long id) throws BillingNotFoundException {
        return billingRepository.findById(id).map(billing -> {
            billingRepository.delete(billing);
            return billing.getId();
        }).orElseThrow(() -> new BillingNotFoundException("Billing"));
    }

}
