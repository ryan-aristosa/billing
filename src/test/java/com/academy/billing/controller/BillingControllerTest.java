package com.academy.billing.controller;

import com.academy.billing.enums.BillingType;
import com.academy.billing.model.Billing;
import com.academy.billing.service.BillingServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class BillingControllerTest {

    @MockBean
    private BillingServiceImpl billingServiceImpl;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    BigDecimal amount1 = new BigDecimal("200.09");
    BigDecimal amount2 = new BigDecimal("400.09");
    BigDecimal amountUpdate = new BigDecimal("500.1");
    Billing test1, test2;

    @BeforeEach
    void setup() {

        test1 = new Billing();
        test1.setId(10001L);
        test1.setAccountId(1L);
        test1.setAccountName("test1");
        test1.setAmount(amount1);
        test1.setBillingType(BillingType.PAPER);

        test2 = new Billing();
        test2.setId(10002L);
        test2.setAccountId(2L);
        test2.setAccountName("test2");
        test2.setAmount(amount2);
        test2.setBillingType(BillingType.ELECTRONIC);
    }

    @Test
    @DisplayName("GIVEN the BillingRepository " +
            "WHEN SaveBilling is executed " +
            "THEN result should return test1")
    void testSaveBilling() throws Exception {
        //arrange
        when(billingServiceImpl.saveBilling(any(Billing.class))).thenReturn(test1);
        //act
        //assert
        mockMvc.perform(post("/billing")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(test1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(test1.getId()))
                .andExpect(jsonPath("$.accountName").value(test1.getAccountName()))
                .andExpect(jsonPath("$.amount").value(test1.getAmount()));

    }

    @Test
    @DisplayName("GIVEN the Id=10001L" +
            "WHEN getEmployeeById is executed with value = 1 " +
            "THEN result should return test1")
    void testGetEmployeeById() throws Exception {
        //arrange
        when(billingServiceImpl.findBillingById(anyLong())).thenReturn(test1);
        //act
        // assert
        mockMvc.perform(get("/billing/{id}", 10001L)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(test1.getId()))
                .andExpect(jsonPath("$.accountName").value(test1.getAccountName()))
                .andExpect(jsonPath("$.amount").value(test1.getAmount()));

    }

    @Test
    @DisplayName("GIVEN the BillingRepository" +
            "WHEN GetBilling is executed" +
            "THEN result should return allBilling")
    void testGetBilling(Pageable pageable) throws Exception {
        //arrange
        Page<Billing> billings = new PageImpl<>(List.of(test1, test2));
        when(billingServiceImpl.findAllBillings(pageable)).thenReturn((Page<Billing>) billings);
        //act
        // assert
        mockMvc.perform(get("/billing")).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(test1.getId()))
                .andExpect(jsonPath("$.[0].accountName").value(test1.getAccountName()))
                .andExpect(jsonPath("$.[0].amount").value(test1.getAmount()))
                .andExpect(jsonPath("$.[1].id").value(test2.getId()))
                .andExpect(jsonPath("$.[1].accountName").value(test2.getAccountName()))
                .andExpect(jsonPath("$.[1].amount").value(test2.getAmount()));

    }
    @Test
    @DisplayName("GIVEN the Id=10001L " +
            "WHEN UpdateBilling is executed " +
            "THEN result should return the updated test1")
    void testUpdateBilling() throws Exception {
        //arrange
        test1.setAmount(amountUpdate);
        when(billingServiceImpl.updateBilling(anyLong(), any(Billing.class))).thenReturn(test1);
        //act
        //assert
        mockMvc.perform(put("/billing/{id}", 10001L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(test1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(test1.getAmount()));

    }

    @Test
    @DisplayName("GIVEN the Id=10001L " +
            "WHEN UpdateBilling is executed " +
            "THEN result should return the updated test1")
    void testDeleteBilling() throws Exception {
        //arrange
        doNothing().when(billingServiceImpl).deleteBilling(test1.getId());
        //act
        //assert
        mockMvc.perform(delete("/billing/{id}", 10001L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(test1)))
                .andExpect(status().isOk());

    }

}
