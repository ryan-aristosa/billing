package com.academy.billing.repository;

import com.academy.billing.enums.BillingType;
import com.academy.billing.model.Billing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class BillingRepositoryTest {

    @Autowired
    private BillingRepository billingRepository;

    private final BigDecimal AMOUNT = new BigDecimal("500.0");


//    @Test
//    void testSave() {
//        // Arrange
//
//        Billing billing = new Billing(10004L, 1004L, "Roronoa", AMOUNT, BillingType.ELECTRONIC);
//        // Act
//        Billing savedBilling = billingRepository.save(billing);
//        // Assert
//        assertEquals(billing, savedBilling);
//    }
//
//    @Test
//    void testFindById() {
//        // Arrange
//        BigDecimal amount = new BigDecimal("100.0");
//        Billing billing = new Billing(10001L, 101L, "Ryan", amount, BillingType.PAPER);
//        Billing savedBilling = billingRepository.save(billing);
//        // Act
//        Optional<Billing> existingBilling = billingRepository.findById(savedBilling.getId());
//        // Assert
//        assertTrue(existingBilling.isPresent());
//        assertEquals(savedBilling, existingBilling.get());
//    }
//
//    @Test
//    void testFindAll() {
//        BigDecimal amount = new BigDecimal("100.0");
//        Billing billing1 = new Billing(10001L, 101L, "Ryan", amount, BillingType.PAPER);
//        Billing billing2 = new Billing(10002L, 102L, "Baqui", amount, BillingType.ELECTRONIC);
//        Billing billing3 = new Billing(10003L, 103L, "Justin", amount, BillingType.PAPER);
//        Iterable<Billing> existingBillings = billingRepository.saveAll(List.of(billing1, billing2, billing3));
//
//        Pageable pageable = PageRequest.of(0, 5, Sort.by("accountId").ascending());
//        assertThat(billingRepository.findAll(pageable)).containsAll(existingBillings);
//    }

}
