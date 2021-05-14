package com.atm.service.impl;

import com.atm.service.ATMService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class ATMServiceImpl implements ATMService {
    private BigDecimal atmBalance = new BigDecimal(8000);

    @Override
    public BigDecimal getATMBalance () {
        return atmBalance;
    }
}
