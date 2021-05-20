package com.atm.service.impl;

import com.atm.dto.CustomerDTO;
import com.atm.exceptions.ATMException;
import com.atm.exceptions.AccountException;
import com.atm.exceptions.FundException;
import com.atm.service.ATMService;
import com.atm.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CustomerServiceImplTest {

    // todo make it @InjectMock works
    // todo make it @MockBean Work
    @InjectMocks
    private CustomerService customerService = new CustomerServiceImpl();

    private ATMService mockAtmService = mock(ATMService.class);
    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp () {
        customerDTO = CustomerDTO.builder()
                .accountNumber("1234567890")
                .pin("1234")
                .balance(new BigDecimal(900))
                .overDraft(new BigDecimal(100))
                .build();
        customerService.addCustomer(customerDTO);
    }

    @Test
    void shouldAddCustomerTest () {
        assertEquals(customerDTO, customerService.addCustomer(customerDTO));
    }

    @Test
    void shouldGetCustomerTest () {
        customerDTO.setAccountNumber("0987654321");
        customerService.addCustomer(customerDTO);

        assertEquals(
                customerDTO,
                customerService.getCustomer(customerDTO.getAccountNumber(), customerDTO.getPin())
        );
    }

    @Test
    void shouldGetCustomerWhereCustomerIsNotAddedTest () {
        customerDTO.setAccountNumber("123123123");
        String accountNumber = customerDTO.getAccountNumber();
        String pin = customerDTO.getPin();
        AccountException accountException = assertThrows(AccountException.class,
                () -> customerService.getCustomer(accountNumber, pin));

        assertEquals("ACCOUNT_Err: User not found by given username and Pin please try again",
                accountException.getMessage());
    }

    @Test
    void shouldWithdrawAmountTest () {
        BigDecimal withdrawAmount = new BigDecimal(100);

        when(mockAtmService.getATMBalance()).thenReturn(new BigDecimal(8000));

//        when(mockAtmService.getATMBalance()).thenReturn(new BigDecimal(7000));
        CustomerDTO customerDTOAfterWithdrawal =
                customerService.withdrawAmount(customerDTO.getAccountNumber(), withdrawAmount);

        assertNotNull(customerDTOAfterWithdrawal);
        assertEquals(new BigDecimal(800), customerDTOAfterWithdrawal.getBalance());
        assertEquals(customerDTO.getOverDraft(), customerDTOAfterWithdrawal.getOverDraft());

//
//        verify(mockAtmService).setBalance(800);
//        verify(mockAtmService).getATMBalance();
//        verifyNoMoreInteractions(mockAtmService);
    }

    @Test
    void shouldWithdrawFullAmountTest () {
        BigDecimal withdrawAmount = new BigDecimal(1000);
        BigDecimal zeroAmount = new BigDecimal(0);
        CustomerDTO customerDTOAfterWithdrawal =
                customerService.withdrawAmount(customerDTO.getAccountNumber(), withdrawAmount);

        assertNotNull(customerDTOAfterWithdrawal);
        assertEquals(zeroAmount, customerDTOAfterWithdrawal.getBalance());
        assertEquals(zeroAmount, customerDTOAfterWithdrawal.getOverDraft());
    }

    @Test
    void shouldWithdrawFullBalanceOnlyTest () {
        BigDecimal withdrawAmount = new BigDecimal(900);
        BigDecimal zeroAmount = new BigDecimal(0);
        CustomerDTO customerDTOAfterWithdrawal =
                customerService.withdrawAmount(customerDTO.getAccountNumber(), withdrawAmount);

        assertNotNull(customerDTOAfterWithdrawal);
        assertEquals(zeroAmount, customerDTOAfterWithdrawal.getBalance());
        assertEquals(customerDTO.getOverDraft(), customerDTOAfterWithdrawal.getOverDraft());
    }

    @Test
    void shouldWithdrawFullBalanceAndSomeOfOverDraftAmountTest () {
        BigDecimal withdrawAmount = new BigDecimal(950);
        CustomerDTO customerDTOAfterWithdrawal =
                customerService.withdrawAmount(customerDTO.getAccountNumber(), withdrawAmount);

        assertNotNull(customerDTOAfterWithdrawal);
        assertEquals(new BigDecimal(0), customerDTOAfterWithdrawal.getBalance());
        assertEquals(new BigDecimal(50), customerDTOAfterWithdrawal.getOverDraft());
    }

    @Test
    void shouldWithdrawMoreAmountThanFullBalanceAndOverDraftAmount_ShouldThrowFundExceptionTest () {
        BigDecimal withdrawAmount = new BigDecimal(1100);
        String accountNumber = customerDTO.getAccountNumber();

        FundException fundException = assertThrows(FundException.class,
                () -> customerService.withdrawAmount(accountNumber, withdrawAmount));

        assertEquals("FUND_ERR: Your Balance is not sufficient to withdraw requested amount",
                fundException.getMessage());
    }

    @Test
    void shouldWithdrawMoreAmountThanATMBalance_ShouldThrowATMExceptionTest () {
        BigDecimal withdrawAmount = new BigDecimal(9000);
        String accountNumber = customerDTO.getAccountNumber();
        ATMException atmException = assertThrows(ATMException.class,
                () -> customerService.withdrawAmount(accountNumber, withdrawAmount));

        assertEquals("ATM_Err: ATM not have sufficient fund, please try again later",
                atmException.getMessage());
    }

    @AfterEach
    void tearDown () {
    }
}