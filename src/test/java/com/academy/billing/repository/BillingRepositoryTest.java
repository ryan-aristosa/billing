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

    private final BigDecimal AMOUNT1 = new BigDecimal("500.0");
    private final Billing BILLING1 = new Billing(10001L, 101L, "Ryan", AMOUNT1, BillingType.PAPER);


    @Test
    void testSave() {
        // Arrange
        // Act
        Billing savedBilling = billingRepository.save(BILLING1);
        // Assert
        assertEquals(BILLING1, savedBilling);
    }

    @Test
    void testFindById() {
        // Arrange
        Billing savedBilling = billingRepository.save(BILLING1);
        // Act
        Optional<Billing> existingBilling = billingRepository.findById(savedBilling.getId());
        // Assert
        assertTrue(existingBilling.isPresent());
        assertEquals(savedBilling, existingBilling.get());
    }

    @Test
    void testFindAll() {
        // Arrange
        BigDecimal amount2 = new BigDecimal("700.0");
        BigDecimal amount3 = new BigDecimal("1200.0");
        Billing billing2 = new Billing(10002L, 102L, "Baqui", amount2, BillingType.ELECTRONIC);
        Billing billing3 = new Billing(10003L, 103L, "Justin", amount3, BillingType.PAPER);
        // Act
        Iterable<Billing> existingBillings = billingRepository.saveAll(List.of(BILLING1, billing2, billing3));
        // Assert
        Pageable pageable = PageRequest.of(0, 5, Sort.by("accountId").ascending());
        assertThat(billingRepository.findAll(pageable)).containsAll(existingBillings);
    }

}
