package com.atm;

import com.atm.controller.ATMController;
import com.atm.service.ATMService;
import com.atm.service.CustomerService;
import com.atm.service.impl.ATMServiceImpl;
import com.atm.service.impl.CustomerServiceImpl;

public class AtmApplication {

    private static ATMController ATMController;
    private static CustomerService customerService = new CustomerServiceImpl();
    private static ATMService atmService = new ATMServiceImpl();

    public static void main (String[] args) {
        ATMController = new ATMController(customerService, atmService);
        ATMController.init();
    }

}
