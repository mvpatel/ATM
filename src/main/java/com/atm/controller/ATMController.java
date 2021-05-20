package com.atm.controller;

import com.atm.dto.CustomerDTO;
import com.atm.exceptions.ATMException;
import com.atm.exceptions.AccountException;
import com.atm.exceptions.FundException;
import com.atm.service.ATMService;
import com.atm.service.CustomerService;

import java.math.BigDecimal;
import java.util.Scanner;

public class ATMController {

    private CustomerService customerService;
    private ATMService atmService;

    private Scanner scanner = new Scanner(System.in);
    private CustomerDTO currentCustomer;

    public ATMController (CustomerService customerService, ATMService atmService) {
        this.customerService = customerService;
        this.atmService = atmService;
    }

    public void init () {
        addDummyCustomersData();
        printATMBalance();
        verifyCustomerDetails();
        printCustomerDetails();
        askCustomerForNextAction();
    }

    private void addDummyCustomersData () {
        CustomerDTO dummyCustomerDTO;
        for (int i = 0; i <= 50; i++) {
            dummyCustomerDTO = CustomerDTO.builder()
                    .accountNumber("123456789" + i)
                    .pin("123" + i)
                    .balance(new BigDecimal(1000))
                    .overDraft(new BigDecimal(100))
                    .build();
            customerService.addCustomer(dummyCustomerDTO);
        }
    }

    private void printATMBalance () {
        System.out.printf("ATM Balance: %s %n", atmService.getATMBalance());
    }

    private void askCustomerForNextAction () {
        printLineSeparator();
        System.out.println("Choose your next Action from Below");
        printLineSeparator();
        System.out.println("For Balance Check: B");
        System.out.println("Cash Withdrawals: W Amount");
        System.out.println("Exit From the System: Exit");
        printLineSeparator();

        String nextLine = scanner.nextLine();

        chooseNextAction(nextLine);
    }

    private void chooseNextAction (String nextLine) {
        if (nextLine.equals("B")) {
            printCustomerDetails();
            printCustomerBalance();
            askCustomerForNextAction();
        } else if (nextLine.contains("W ")) {
            BigDecimal withdrawalAmount = new BigDecimal(nextLine.replace("W ", ""));
            cashWithdrawals(withdrawalAmount);
            printCustomerBalance();
            askCustomerForNextAction();
        } else if (nextLine.equals("Exit")) {
            System.out.println("Bye, Have a great Day See you again!");
            scanner.close();
        } else {
            System.out.println("Given Input is not valid please try again...");
            askCustomerForNextAction();
        }
    }

    private void printLineSeparator () {
        System.out.println("------------------------------------------");
    }

    private void cashWithdrawals (BigDecimal amount) {
        try {
            customerService.withdrawAmount(currentCustomer.getAccountNumber(), amount);
        } catch (FundException fundException) {
            System.out.println(fundException.getMessage());
            askCustomerForNextAction();
        } catch (ATMException atmException) {
            System.out.println(atmException.getMessage());
            askCustomerForNextAction();
        }
    }

    private void printCustomerBalance () {
        System.out.println("Customer Balance: " + currentCustomer.getBalance());
        System.out.println("Customer Overdraft: " + currentCustomer.getOverDraft());
    }

    private void printCustomerDetails () {
        System.out.printf("Customer Account Number: %s, Customer Pin: %s %n",
                currentCustomer.getAccountNumber(),
                currentCustomer.getPin()
        );
    }

    private void verifyCustomerDetails () {
        System.out.println("Enter your Account Number");
        String accountNumber = scanner.nextLine();
        System.out.println("Enter your Pin");
        String pin = scanner.nextLine();

        try {
            currentCustomer = customerService.getCustomer(accountNumber, pin);
        } catch (AccountException accountException) {
            System.out.println(accountException.getMessage());
            verifyCustomerDetails();
        }
    }
}
