package com.academy.billing.service;

import com.academy.billing.enums.BillingType;
import com.academy.billing.model.Billing;
import com.academy.billing.repository.BillingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BillingServiceTest {

    @Mock
    private BillingRepository billingRepository;

    @InjectMocks
    private BillingService billingService = new BillingServiceImpl();

    private Billing baqui;
    private Billing hajime;
    private Billing roronoa;
    private List<Billing> billings;


    @BeforeEach
    public void setup() {
        BigDecimal value = new BigDecimal("100.00");
        BigDecimal value2 = new BigDecimal("200.00");
        BigDecimal value3 = new BigDecimal("300.00");
        baqui = new Billing(10001L, 1001L, "Baqui", value, BillingType.PAPER);
        hajime = new Billing(10002L, 1002L, "Hajime", value2, BillingType.ELECTRONIC);
        roronoa = new Billing(10003L, 1003L, "Zoro", value3, BillingType.PAPER);
        billings = List.of(baqui, hajime, roronoa);
    }

    @Test
    public void testSaveBilling() {
        Mockito.when(billingRepository.save(baqui)).thenReturn(baqui);

        Billing saveBilling = billingService.saveBilling(baqui);

        assertEquals(saveBilling, baqui);
    }

    @Test
    public void testFindAllBillings() {
        Pageable pageable = PageRequest.of(0,20);
        Mockito.when(billingRepository.findAll(pageable))
                .thenReturn(new PageImpl<>(billings, pageable, billings.size()));

        Page<Billing> allBillings = billingService.findAllBillings(pageable);

        assertThat(allBillings).contains(baqui, hajime, roronoa);
    }

    @Test
    public void testFindBillingById() {
        Mockito.when(billingRepository.findById(baqui.getId())).thenReturn(Optional.ofNullable(baqui));

        Billing billingById = billingService.findBillingById(10001L);

        assertEquals(billingById, baqui);
    }

    @Test
    public void testUpdateBilling(){
        Mockito.when(billingRepository.findById(10001L)).thenReturn(Optional.ofNullable(baqui));
        Mockito.when(billingRepository.save(baqui)).thenReturn(baqui);
        BigDecimal amount = new BigDecimal("400.00");
        Billing newBaqui = new Billing(10001L, 1001L, "Baqui", amount, BillingType.ELECTRONIC);

        Billing updateBilling = billingService.updateBilling(10001L, newBaqui);

        assertEquals(baqui, updateBilling);
    }
    @Test
    public void testDeleteBilling(){
        Long idToDelete = 10001L;
        Mockito.when(billingRepository.findById(idToDelete)).thenReturn(Optional.ofNullable(baqui));

        Long deletedBilling = billingService.deleteBilling(10001L);

        assertEquals(deletedBilling, idToDelete);
    }

}
