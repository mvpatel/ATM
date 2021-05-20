package com.atm.service.impl;

import com.atm.service.ATMService;

import java.math.BigDecimal;
public class ATMServiceImpl implements ATMService {
    private BigDecimal atmBalance = new BigDecimal(8000);

    @Override
    public void setBalance (BigDecimal atmBalance) {
        this.atmBalance = atmBalance;
    }

    @Override
    public BigDecimal getATMBalance () {
        return atmBalance;
    }
}
