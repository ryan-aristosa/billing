package com.academy.billing.service;

import com.academy.billing.model.Billing;
import com.academy.billing.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillingServiceImpl implements BillingService {



    @Autowired
    BillingRepository billingRepository;



    @Override
    public Page<Billing> findAllAccounts(Pageable pageable) {
        Page<Billing> pageBilling = billingRepository.findAll(pageable);
        return pageBilling;
    }



    @Override
    public Billing findAccountById(Long id) {
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
        billingOptional.get().setName(billing.getName());
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
