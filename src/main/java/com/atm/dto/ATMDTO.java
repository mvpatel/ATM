package com.atm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ATMDTO {
    private BigDecimal balance = new BigDecimal("8000");
}
