package com.atm.service;

import com.atm.service.impl.ATMServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ATMServiceTest {

    ATMService atmService = new ATMServiceImpl();

    @BeforeEach
    void setUp () {
    }

    @AfterEach
    void tearDown () {
    }

    @Test
    void shouldSetATMBalance() {
        BigDecimal atmBalanceToBeSet = new BigDecimal(10000);
        atmService.setBalance(atmBalanceToBeSet);
        assertEquals(atmBalanceToBeSet,atmService.getATMBalance());
    }

    @Test
    void shouldGetATMBalance() {
        BigDecimal atmBalanceToBeGet = new BigDecimal(10000);
        atmService.setBalance(atmBalanceToBeGet);
        assertEquals(atmBalanceToBeGet,atmService.getATMBalance());

    }
}