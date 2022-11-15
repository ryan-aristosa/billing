package com.academy.billing;

import com.academy.billing.model.Billing;
import com.academy.billing.repository.BillingRepository;
import com.academy.billing.service.BillingService;
import com.academy.billing.service.BillingServiceImpl;
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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BillingServiceTests {

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
        baqui = new Billing(10001L, 101L, "Baqui", value, "PAPER");
        hajime = new Billing(10002L, 102L, "Hajime", value2, "ELECTRONICS");
        roronoa = new Billing(10003L, 103L, "Zoro", value3, "PAPER");
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
        Mockito.when(billingRepository.findById(10001L))
            .thenReturn(Optional.ofNullable(baqui));

        Mockito.when(billingRepository.save(baqui))
                .thenReturn(baqui);

        BigDecimal amount = new BigDecimal("400.00");
        Billing newBaqui = new Billing(10001L, 10001L, "Trafalgar", amount, "Electronics");

        Billing updateBilling = billingService.updateBilling(10001L, newBaqui);

        assertEquals(baqui, updateBilling);
    }
    @Test
    public void testDeleteBilling(){
        Mockito.when(billingRepository.findById(10001L)).thenReturn(Optional.ofNullable(baqui));

        billingService.deleteBilling(10001L);

        verify(billingRepository).delete(baqui);
    }

}
