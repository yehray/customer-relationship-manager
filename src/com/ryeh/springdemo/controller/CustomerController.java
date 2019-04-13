package com.ryeh.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ryeh.springdemo.dao.CustomerDAO;
import com.ryeh.springdemo.entity.Customer;
import com.ryeh.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public String listCustomers(Model theModel) {	
		// get customers form the dao
		List<Customer> theCustomers = customerService.getCustomers();
		
		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);		
		
		return "list-customers";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		//create model attribute to bind form data
		Customer theCustomer = new Customer();

		theModel.addAttribute("customer", theCustomer);

		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer){
		// save the customer using the service
		customerService.saveCustomer(theCustomer);

		return "redirect:/customer/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel){
		//get customer from service
		Customer theCustomer = customerService.getCustomer(theId);
		// set customer as a model attribute to prepopulate the form
		theModel.addAttribute("customer", theCustomer);
		//send over the form
		return "customer-form";
	}


	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId, Model theModel){
		//delete customer
		customerService.deleteCustomer(theId);

		return "redirect:/customer/list";
	}
}
