package com.atm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CustomerDTO {
    private String accountNumber;
    private String pin;
    private BigDecimal balance;
    private BigDecimal overDraft;
    private BigDecimal withdrawalBalance;
}
