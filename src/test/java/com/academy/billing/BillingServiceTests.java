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
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BillingServiceTests {
//    @Mock
//    private BillingRepository billingRepository;
//
//    @InjectMocks
//    private BillingService billingService = new BillingServiceImpl();
//    BigDecimal value = new BigDecimal("100.00");
//    BigDecimal value2 = new BigDecimal("200.00");
//    BigDecimal value3 = new BigDecimal("300.00");
//
//    Billing Baqui = new Billing(1L, 1L, "baqui", value, "paper");
//    Billing Hajime = new Billing(2L, 2L, "hajime", value2, "electronics");
//    Billing Roronoa = new Billing(3L, 3L, "zoro", value3, "paper");
//    List<Billing> billings = List.of(Baqui, Hajime, Roronoa);
//
//
//    @Test
//    public void testFindAllBillings(Pageable pageable) {
//        Mockito.when(billingRepository.findAll(pageable))
//                .thenReturn(new PageImpl<>(billings, pageable, billings.size()));
//
//        Page<Billing> allBillings = billingService.findAllBillings(pageable);
//
//        assertThat(allBillings).contains(Baqui, Hajime, Roronoa);
//
//    }
//
//    @Test
//    public void testFindBillingById() {
//        Mockito.when(billingRepository.findById(Baqui.getId()))
//                .thenReturn(Optional.ofNullable(Baqui));
//
//        Billing billingById = billingService.findBillingById(1L);
//
//        assertEquals(billingById, Baqui);
//    }
//
//    @Test
//    public void testSaveBilling() {
//        Mockito.when(billingRepository.save(Baqui))
//                .thenReturn(Baqui);
//
//        Billing saveBilling = billingService.saveBilling(Baqui);
//
//        assertEquals(saveBilling, Baqui);
//    }
//
//    @Test
//    public void testUpdateBilling(){
//        Mockito.when(billingRepository.findById(1L))
//            .thenReturn(Optional.ofNullable(Baqui));
//
//        Mockito.when(billingRepository.save(Baqui))
//                .thenReturn(Baqui);
//
//
//        BigDecimal amount = new BigDecimal("400.00");
//        Billing newBaqui = new Billing(1L, 1L, "Trafalgar", amount, "Electronics");
//
//        Billing updateBilling = billingService.updateBilling(1L, newBaqui);
//
//        assertEquals(Baqui, updateBilling);
//    }
//    @Test
//    public void testDeleteBilling(){
//
//        Mockito.when(billingRepository.findById(1L))
//                .thenReturn(Optional.ofNullable(Baqui));
//
//        billingService.deleteBilling(1L);
//
//        verify(billingRepository).delete(Baqui);
//    }
}
