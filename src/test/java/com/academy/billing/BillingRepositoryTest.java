package com.academy.billing;

import com.academy.billing.model.Billing;
import com.academy.billing.repository.BillingRepository;
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


//    @Test
//    void testSave() {
//        // Arrange
//        BigDecimal amount = new BigDecimal("100.0");
//        Billing billing = new Billing(101L, "Ryan", amount, "Paper");
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
//        Billing billing = new Billing(101L, "Ryan", amount, "Paper");
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
//        Billing billing1 = new Billing(101L, "Ryan", amount, "Paper");
//        Billing billing2 = new Billing(102L, "Baqui", amount, "Paper");
//        Billing billing3 = new Billing(103L, "Justin", amount, "Electronic");
//        Iterable<Billing> existingBillings = billingRepository.saveAll(List.of(billing1, billing2, billing3));
//
//        Pageable pageable = PageRequest.of(0, 5, Sort.by("accountId").ascending());
//        assertThat(billingRepository.findAll(pageable)).containsAll(existingBillings);
//    }

}
