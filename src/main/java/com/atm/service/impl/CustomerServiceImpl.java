package com.atm.service.impl;

import com.atm.dto.CustomerDTO;
import com.atm.exceptions.ATMException;
import com.atm.exceptions.AccountException;
import com.atm.exceptions.FundException;
import com.atm.service.ATMService;
import com.atm.service.CustomerService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CustomerServiceImpl implements CustomerService {

    // make Autowired working
    private ATMService atmService = new ATMServiceImpl();


    private Map<String, CustomerDTO> customers = new HashMap<>();

    /**
     * @inheritDoc
     */
    @Override
    public CustomerDTO addCustomer (CustomerDTO customerDTO) {
        customers.put(customerDTO.getAccountNumber(), customerDTO);
        return customers.get(customerDTO.getAccountNumber());
    }

    /**
     * @inheritDoc
     */
    @Override
    public CustomerDTO withdrawAmount (String accountNumber, BigDecimal amount) throws ATMException, FundException {
        CustomerDTO currentCustomer = customers.get(accountNumber);
        BigDecimal currentCustomerBalance = currentCustomer.getBalance();
        BigDecimal currentCustomerOverDraft = currentCustomer.getOverDraft();

        if (atmService.getATMBalance().compareTo(amount) < 0) {
            throw new ATMException("ATM_Err: ATM not have sufficient fund, please try again later");
        } else if (currentCustomerBalance.compareTo(amount) >= 0) {
            currentCustomer.setBalance(currentCustomerBalance.subtract(amount));
            atmService.setBalance(atmService.getATMBalance().subtract(amount));
            customers.put(currentCustomer.getAccountNumber(), currentCustomer);

            return customers.get(currentCustomer.getAccountNumber());

        } else if (currentCustomerBalance.compareTo(amount) < 0
                && currentCustomerOverDraft.compareTo(amount.subtract(currentCustomerBalance)) >= 0) {
            currentCustomer.setBalance(new BigDecimal(0));
            currentCustomer.setOverDraft(currentCustomer.getOverDraft().subtract(amount.subtract(currentCustomerBalance)));
            atmService.setBalance(atmService.getATMBalance().subtract(amount));
            return customers.get(currentCustomer.getAccountNumber());
        }
        throw new FundException("FUND_ERR: Your Balance is not sufficient to withdraw requested amount");
    }

    /**
     * @inheritDoc
     */
    @Override
    public CustomerDTO getCustomer (String accountNumber, String pin) throws AccountException {
        CustomerDTO customerDTO = customers.get(accountNumber);
        if (Objects.nonNull(customerDTO) && customerDTO.getPin().equals(pin)) {
            return customers.get(accountNumber);
        }
        throw new AccountException("ACCOUNT_Err: User not found by given username and Pin please try again");
    }
}
