package com.academy.billing.model;

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
    @SequenceGenerator(name = "idSeqGen", sequenceName = "idSeq", initialValue = 10001)
    @GeneratedValue(generator = "idSeqGen")
    @Column(name = "id", nullable = false)
    private Long id;
    private Long accountId;
    private String accountName;
    private BigDecimal amount;
    private String type;

}
