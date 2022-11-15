package com.academy.billing.controller;

import com.academy.billing.exception.RecordNotFoundException;
import com.academy.billing.model.Billing;
import com.academy.billing.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("billing")
public class BillingController {

    @Autowired
    private BillingService billingService;


    @PostMapping()
    public ResponseEntity<Billing> saveBilling(@RequestBody Billing billing) {
        Billing saveBilling = billingService.saveBilling(billing);
        return new ResponseEntity<>(saveBilling, HttpStatus.OK);
    }

    // Getting all billings
    @GetMapping()
    public ResponseEntity<Page<Billing>> getBilling(Pageable pageable) {
        Page<Billing> billings = billingService.findAllBillings(pageable);
        return new ResponseEntity<>(billings, HttpStatus.OK);
    }

    // Getting billing by id
    @GetMapping("/{id}")
    public ResponseEntity<Billing> getBillingById(@PathVariable Long id) throws RecordNotFoundException {
        Billing billingById = billingService.findBillingById(id);
        return new ResponseEntity<>(billingById, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Billing> updateBilling(
            @PathVariable Long id,
            @RequestBody Billing newBilling
    ) throws RecordNotFoundException {
        Billing update = billingService.updateBilling(id, newBilling);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBilling(@PathVariable Long id) throws RecordNotFoundException {
        Boolean delete = billingService.deleteBilling(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}
