package com.customermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.customermanagement.Service.CustServices;
import com.customermanagement.entities.Customers;

@Controller
@RequestMapping("/customers")
public class Controllers {

	@Autowired
	private CustServices customerService;

	@RequestMapping("/list")
	public String listCustomers(Model model) {
		List<Customers> customers = customerService.findAll();

		model.addAttribute("Customers", customers);

		return "list";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// get the customer from the service

		Customers customer = new Customers();
		// set Customer as a model attribute to pre-populate the form
		theModel.addAttribute("Customer", customer);

		// send over to our form
		return "form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int theId, Model theModel) {

		// get the customer from the service
		Customers theCustomer = customerService.findById(theId);

		// set Customer as a model attribute to pre-populate the form
		theModel.addAttribute("Customer", theCustomer);

		// send over to our form
		return "form";
	}

	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id, @RequestParam("firstname") String firstName,
			@RequestParam("lastname") String lastName, @RequestParam("email") String email) {

		System.out.println(id);
		Customers theCustomer;
		if (id != 0) {
			theCustomer = customerService.findById(id);
			theCustomer.setFirstName(firstName);
			theCustomer.setLastName(lastName);
			theCustomer.setEmail(email);
		} else
			theCustomer = new Customers(firstName, lastName, email);
		// save the Customer
		customerService.save(theCustomer);

		// use a redirect to prevent duplicate submissions
		return "redirect:/customers/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int theId) {

		// delete the customer
		customerService.deleteById(theId);

		return "redirect:/customers/list";

	}

}
