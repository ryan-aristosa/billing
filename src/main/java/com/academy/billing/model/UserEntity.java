package com.academy.billing.model;

import com.academy.billing.enums.Role;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role type;

}
