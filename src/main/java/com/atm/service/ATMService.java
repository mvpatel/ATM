package com.atm.service;

import java.math.BigDecimal;
public interface ATMService {
    void setBalance (BigDecimal atmBalance);

    public BigDecimal getATMBalance();
}
