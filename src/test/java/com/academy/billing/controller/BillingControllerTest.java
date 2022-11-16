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
import org.springframework.data.domain.PageRequest;
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

    private Billing test1, test2;
    private Long id;


    @BeforeEach
    void setup() {
        BigDecimal amount1 = new BigDecimal("200.09");
        BigDecimal amount2 = new BigDecimal("400.09");
        test1 = new Billing(10001L, 1001L, "test1", amount1, BillingType.PAPER);
        test2 = new Billing(10002L, 1002L, "test2", amount2, BillingType.ELECTRONIC);
        id = 10001L;
    }

    @Test
    @DisplayName("GIVEN the BillingRepository " +
            "WHEN saveBilling() is executed " +
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
    @DisplayName("GIVEN the BillingRepository" +
            "WHEN findBillingById() is executed with value = 10001L " +
            "THEN result should return test1")
    void testGetBillingById() throws Exception {
        //arrange
        when(billingServiceImpl.findBillingById(anyLong())).thenReturn(test1);
        //act
        // assert
        mockMvc.perform(get("/billing/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(test1.getId()))
                .andExpect(jsonPath("$.accountName").value(test1.getAccountName()))
                .andExpect(jsonPath("$.amount").value(test1.getAmount()));
    }

    @Test
    @DisplayName("GIVEN the BillingRepository" +
            "WHEN findAllBillings() is executed " +
            "THEN result should return all billings")
    void testGetBilling() throws Exception {
        //arrange
        List<Billing> billingList = List.of(test1, test2);
        Pageable pageable = PageRequest.of(0,20);
        Page<Billing> billings = new PageImpl<>(billingList, pageable, billingList.size());
        when(billingServiceImpl.findAllBillings(pageable)).thenReturn(billings);
        //act
        // assert
        mockMvc.perform(get("/billing")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].id").value(test1.getId()))
                .andExpect(jsonPath("$.content.[0].accountName").value(test1.getAccountName()))
                .andExpect(jsonPath("$.content.[0].amount").value(test1.getAmount()))
                .andExpect(jsonPath("$.content.[1].id").value(test2.getId()))
                .andExpect(jsonPath("$.content.[1].accountName").value(test2.getAccountName()))
                .andExpect(jsonPath("$.content.[1].amount").value(test2.getAmount()));
    }

    @Test
    @DisplayName("GIVEN the BillingRepository " +
            "WHEN updateBilling() is executed with value = 10001L " +
            "THEN result should return the updated test1")
    void testUpdateBilling() throws Exception {
        //arrange
        BigDecimal amountUpdate = new BigDecimal("500.1");
        test1.setAmount(amountUpdate);
        when(billingServiceImpl.updateBilling(anyLong(), any(Billing.class))).thenReturn(test1);
        //act
        //assert
        mockMvc.perform(put("/billing/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(test1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(test1.getAmount()));
    }

    @Test
    @DisplayName("GIVEN the BillingRepository " +
            "WHEN deleteBilling() is executed with value = 10001L " +
            "THEN result should return the id of deleted test1")
    void testDeleteBilling() throws Exception {
        //arrange
        doNothing().when(billingServiceImpl).deleteBilling(id);
        //act
        //assert
        mockMvc.perform(delete("/billing/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(test1)))
                .andExpect(status().isNoContent());
    }

}
