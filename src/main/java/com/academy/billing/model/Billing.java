package com.academy.billing.model;

import com.academy.billing.enums.BillingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Billing extends BaseAuditClass {

    @Id
    @SequenceGenerator(name = "billingIdSeqGen", sequenceName = "billingIdSeq", initialValue = 10001)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "billingIdSeq")
    @Column(name = "id", nullable = false)
    private Long id;

    private Long accountId;
    private String accountName;
    private BigDecimal amount;

    @Enumerated(value = EnumType.STRING)
    private BillingType type;

}
