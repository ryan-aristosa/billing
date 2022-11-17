package com.academy.billing.model;

import com.academy.billing.enums.Role;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class UserEntity extends BaseAuditClass {

    @Id
    @SequenceGenerator(name = "userIdSeqGen", sequenceName = "userIdSeq", initialValue = 1001)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "userIdSeq")
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role type;

}
