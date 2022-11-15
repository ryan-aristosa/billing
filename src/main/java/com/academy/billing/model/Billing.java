package com.academy.billing.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Billing extends BaseAuditClass {

    @Id
    @SequenceGenerator(name = "idSeqGen", sequenceName = "idSeq", initialValue = 10001)
    @GeneratedValue(generator = "idSeqGen")
    @Column(name = "id", nullable = false)
    private Long id;
    private Long accountId;
    private String accountName;
    private BigDecimal amount;
    private String type;


    public Billing() {
    }

    public Billing(Long accountId, String accountName, BigDecimal amount, String type) {
        this.accountId = accountId;
        this.accountName = accountName;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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
