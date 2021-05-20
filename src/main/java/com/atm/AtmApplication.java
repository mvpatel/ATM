package com.atm;

import com.atm.controller.CustomerController;
import com.atm.service.ATMService;
import com.atm.service.CustomerService;
import com.atm.service.impl.ATMServiceImpl;
import com.atm.service.impl.CustomerServiceImpl;

public class AtmApplication {

	private static CustomerController customerController;
	private static CustomerService customerService = new CustomerServiceImpl();
	private static ATMService atmService = new ATMServiceImpl();

	public static void main(String[] args) {
		customerController = new CustomerController(customerService, atmService);
		customerController.init();
	}

}
