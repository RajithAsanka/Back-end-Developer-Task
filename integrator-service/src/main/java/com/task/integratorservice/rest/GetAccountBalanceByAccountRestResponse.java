package com.task.integratorservice.rest;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Developed by Rajith Asanka - 901833109V
 */

@Getter
@Setter
public class GetAccountBalanceByAccountRestResponse extends CommonRestResponse {

    private String accountNo;

    private String userId;

    private BigDecimal balance;
}
