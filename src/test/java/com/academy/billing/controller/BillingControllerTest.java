package com.academy.billing.controller;


import com.academy.billing.BillingApplication;
import com.academy.billing.enums.BillingType;
import com.academy.billing.model.Billing;
import com.academy.billing.service.BillingServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = BillingApplication.class)
public class BillingControllerTest {

    @MockBean
    private BillingServiceImpl billingServiceImpl;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private Billing test1, test2;
    private Long id;


    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilter(springSecurityFilterChain).build();
        BigDecimal amount1 = new BigDecimal("200.09");
        BigDecimal amount2 = new BigDecimal("400.09");
        test1 = new Billing(10001L, 1001L, "test1", amount1, BillingType.PAPER);
        test2 = new Billing(10002L, 1002L, "test2", amount2, BillingType.ELECTRONIC);
        id = 10001L;
    }



    private String obtainAccessToken(String username, String password) throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", "group1-client");
        params.add("username", username);
        params.add("password", password);

        ResultActions result
                = mockMvc.perform(post("/login")
                        .params(params)
                        .with(httpBasic("group1-client","group1-password"))
                        .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }

    @Test
    @DisplayName("GIVEN No TOKEN " +
            "WHEN GetSecureRequest is executed " +
            "THEN Unauthorized")
    public void testNoToken() throws Exception {
        mockMvc.perform(get("/billing"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("GIVEN Invalid Role " +
            "WHEN PostSecureRequest is executed " +
            "THEN Forbidden")
    public void testInvalidRole() throws Exception {
        String accessToken = obtainAccessToken("Justin10", "tintin10");
        mockMvc.perform(post("/billing")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("GIVEN the BillingRepository " +
            "WHEN saveBilling() is executed " +
            "THEN result should return test1")
    void testSaveBilling() throws Exception {
        //arrange
        String accessToken = obtainAccessToken("ruan7", "rU4n-dagreyt");
        when(billingServiceImpl.saveBilling(any(Billing.class))).thenReturn(test1);
        //act
        //assert
        mockMvc.perform(post("/billing")
                        .header("Authorization", "Bearer " + accessToken)
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
        String accessToken = obtainAccessToken("ruan7", "rU4n-dagreyt");
        when(billingServiceImpl.findBillingById(anyLong())).thenReturn(test1);
        //act
        // assert
        mockMvc.perform(get("/billing/{id}", id)
                        .header("Authorization", "Bearer " + accessToken))
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
        String accessToken = obtainAccessToken("ruan7", "rU4n-dagreyt");
        List<Billing> billingList = List.of(test1, test2);
        Pageable pageable = PageRequest.of(0,20);
        Page<Billing> billings = new PageImpl<>(billingList, pageable, billingList.size());
        when(billingServiceImpl.findAllBillings(pageable)).thenReturn(billings);
        //act
        // assert
        mockMvc.perform(get("/billing")
                        .header("Authorization", "Bearer " + accessToken)
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
        String accessToken = obtainAccessToken("ruan7", "rU4n-dagreyt");
        BigDecimal amountUpdate = new BigDecimal("500.1");
        test1.setAmount(amountUpdate);
        when(billingServiceImpl.updateBilling(anyLong(), any(Billing.class))).thenReturn(test1);
        //act
        //assert
        mockMvc.perform(put("/billing/{id}", id)
                        .header("Authorization", "Bearer " + accessToken)
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
        String accessToken = obtainAccessToken("ruan7", "rU4n-dagreyt");
        doNothing().when(billingServiceImpl).deleteBilling(id);
        //act
        //assert
        mockMvc.perform(delete("/billing/{id}", id)
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(test1)))
                .andExpect(status().isOk());
    }

}
