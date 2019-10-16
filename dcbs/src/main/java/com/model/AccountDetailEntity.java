package com.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class AccountDetailEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String userId;

    private String accountNo;

    private BigDecimal balance;


}


