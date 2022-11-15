package com.academy.billing.service;

import com.academy.billing.exception.RecordNotFoundException;
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
    public Billing saveBilling(Billing billing) {
        return billingRepository.save(billing);
    }

    @Override
    public Page<Billing> findAllBillings(Pageable pageable) {
        return billingRepository.findAll(pageable);
    }

    @Override
    public Billing findBillingById(Long id) throws RecordNotFoundException {
        return billingRepository.findById(id).orElseThrow(RecordNotFoundException::new);

//        Optional<Billing> billingOptional = billingRepository.findById(id);
//        if (billingOptional.isPresent()) {
//            return billingOptional.get();
//        } else {
//            throw new RecordNotFoundException();
//        }
    }

    @Override
    public Billing updateBilling(Long id, Billing newBilling) throws RecordNotFoundException {
        return billingRepository.findById(id).map(billing -> {
            billing.setAmount(newBilling.getAmount());
            billing.setType(newBilling.getType());
            return billingRepository.save(billing);
        }).orElseThrow(RecordNotFoundException::new);

//        Optional<Billing> billingOptional = billingRepository.findById(id);
//        if (billingOptional.isPresent()) {
//            Billing billing = billingOptional.get();
//            billing.setAmount(newBilling.getAmount());
//            billing.setType(newBilling.getType());
//            return billingRepository.save(billing);
//        } else {
//            throw new RecordNotFoundException();
//        }
    }

    @Override
    public boolean deleteBilling(Long id) throws RecordNotFoundException {
        return billingRepository.findById(id).map(billing -> {
            billingRepository.delete(billing);
            return true;
        }).orElseThrow(RecordNotFoundException::new);

//        Optional<Billing> billingOptional = billingRepository.findById(id);
//        if (billingOptional.isPresent()) {
//            billingRepository.delete(billingOptional.get());
//        } else {
//            throw new RecordNotFoundException();
//        }
    }

}
