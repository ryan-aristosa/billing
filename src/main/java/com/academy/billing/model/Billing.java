package com.academy.billing.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Billing extends BaseAuditClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long accountId;
    private String name;
    private BigDecimal amount;
    private String type;

    public Billing() {
    }

    public Billing(Long id, Long accountId, String name, BigDecimal amount, String type) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.amount = amount;
        this.type = type;
    }
    public Billing(Long accountId, String name, BigDecimal amount, String type) {
        this.accountId = accountId;
        this.name = name;
        this.amount = amount;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
