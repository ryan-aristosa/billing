package com.academy.billing.controller;

import com.academy.billing.exception.RecordNotFoundException;
import com.academy.billing.model.Billing;
import com.academy.billing.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;




@RestController
@RequestMapping("billing")
public class BillingController {

    @Autowired
    private BillingService billingService;


    @PostMapping()
    public Billing saveBilling(@RequestBody Billing billing) {
        return billingService.saveBilling(billing);
    }

    // Getting all billings
    @GetMapping()
    public ResponseEntity<Page<Billing>> getBilling(Pageable pageable) {
        Page<Billing> billings = billingService.findAllBillings(pageable);
        return new ResponseEntity<>(billings, HttpStatus.OK);
    }

    // Getting billing by id
    @GetMapping("/{id}")
    public Billing getBillingById(@PathVariable Long id) throws RecordNotFoundException {
        return billingService.findBillingById(id);
    }

    @PutMapping("/{id}")
    public Billing updateBilling(
            @PathVariable Long id,
            @RequestBody Billing newBilling
    ) throws RecordNotFoundException {
        return billingService.updateBilling(id, newBilling);
    }

    @DeleteMapping("/{id}")
    public void deleteBilling(@PathVariable Long id) throws RecordNotFoundException {
        billingService.deleteBilling(id);
    }

}
