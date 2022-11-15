package com.academy.billing.service;

import com.academy.billing.model.Billing;
import com.academy.billing.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

@Service
public class BillingServiceImpl implements BillingService {



    @Autowired
    BillingRepository billingRepository;


    @Override
    public Page<Billing> findAllBillings(Pageable pageable) {
        Page<Billing> billing =  billingRepository.findAll(pageable);

        return billing;
    }

    @Override
    public Billing findBillingById(Long id) {
        Optional<Billing> billingOptional = billingRepository.findById(id);
        return billingOptional.get();
    }

    @Override
    public Billing saveBilling(Billing saveBilling) {
        Billing billing = billingRepository.save(saveBilling);
        return billing;
    }



    @Override
    public Billing updateBilling(Long id, Billing billing) {
        Optional<Billing> billingOptional = billingRepository.findById(id);
        billingOptional.get().setAccountName(billing.getAccountName());
        billingOptional.get().setAmount(billing.getAmount());
        billingOptional.get().setType(billing.getType());
        return billingRepository.save(billingOptional.get());
    }



    @Override
    public void deleteBilling(Long id) {
        Optional<Billing> billingOptional = billingRepository.findById(id);
        billingRepository.delete(billingOptional.get());

    }
}
