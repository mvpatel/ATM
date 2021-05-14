package com.atm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CustomerDTO {

    @NotBlank
    private String accountNumber;

    @NotBlank
    private String pin;

    @NotNull
    private BigDecimal balance;

    @NotNull
    private BigDecimal overDraft;
}
