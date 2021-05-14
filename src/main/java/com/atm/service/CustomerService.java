package com.atm.service;

import com.atm.dto.CustomerDTO;
import com.atm.exceptions.ATMException;
import com.atm.exceptions.AccountException;
import com.atm.exceptions.FundException;

import java.math.BigDecimal;

public interface CustomerService {
    /**
     * Customer will be added in the Customer Map {@link java.util.Map} and then {@link CustomerDTO} will be returned
     *
     * @param customerDTO: {@link CustomerDTO}
     * @return Customer DTO {@link CustomerDTO} will be returned
     */
    CustomerDTO addCustomer (CustomerDTO customerDTO);

    /**
     * If Amount will be less than customer balance then customer balance will be subtracted by given amount
     * If Amount will be greater than customer balance but less than of total allowed withdrawal amount then
     * overdraft amount will be also subtracted
     * If Amount will be grater than ATM Balance then {@link ATMException} will be thrown
     * If Amount will be grater than total allowed withdrawal amount then {@link FundException} will be thrown
     *
     * @param accountNumber: Account Number
     * @param amount         Amount which required to be withdrawn
     *                       Amount should be equal or smaller to ATM Balance
     *                       Amount should be equal or smaller to Customer balance + Customer Overdraft
     * @return {@link CustomerDTO} should be returned for success full withdrawn request
     */
    CustomerDTO withdrawAmount (String accountNumber, BigDecimal amount);

    /**
     * If given Account Number and password won't be matched then {@link AccountException} will be thrown
     * If given Account Number and password will be matched then {@link CustomerDTO} should be returned
     * @param accountNumber Account Number
     * @param pin           Pin for the Account Number
     * @return {@link CustomerDTO} should be returned for success full journey
     */
    CustomerDTO getCustomer (String accountNumber, String pin);
}
