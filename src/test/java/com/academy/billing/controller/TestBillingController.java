package com.academy.billing.controller;

import com.academy.billing.model.Billing;
import com.academy.billing.service.BillingServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class TestBillingController {

    @MockBean
    private BillingServiceImpl billingServiceImpl;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    BigDecimal amount1 = new BigDecimal("200.09");
    BigDecimal amount2 = new BigDecimal("400.09");
    Billing test1, test2;

    @BeforeEach
    void setup() {
        test1 = new Billing();
        test1.setAccountId(1L);
        test1.setName("test1");
        test1.setAmount(amount1);
        test1.setType("paper");

        test2 = new Billing();
        test2.setAccountId(2L);
        test2.setName("test2");
        test2.setAmount(amount2);
        test2.setType("electronic");
    }


}
