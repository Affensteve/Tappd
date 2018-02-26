package de.tappd.Tappd.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.tappd.Tappd.model.Customer;
import de.tappd.Tappd.repo.CustomerRepository;

@RestController
@RequestMapping("rest")
public class TappdController {
	@Autowired
	CustomerRepository repository;

	@RequestMapping("/save")
	public String process() {
		// save a single Customer
		repository.save(new Customer("Jack", "Smith"));

		// save a list of Customers
		repository.save(Arrays.asList(new Customer("Adam", "Johnson"), new Customer("Kim", "Smith"),
				new Customer("David", "Williams"), new Customer("Peter", "Davis")));

		return "Done";
	}

	@RequestMapping("/findall")
	public String findAll() {
		String result = "";

		for (Customer cust : repository.findAll()) {
			result += cust.toString() + "<br>";
		}

		return result;
	}

	@RequestMapping("/findbyid")
	public String findById(@RequestParam("id") long id) {
		String result = "";
		result = repository.findOne(id).toString();
		return result;
	}

	@RequestMapping("/findbylastname")
	public String fetchDataByLastName(@RequestParam("lastname") String lastName) {
		String result = "";

		for (Customer cust : repository.findByLastNameStartsWithIgnoreCase(lastName)) {
			result += cust.toString() + "<br>";
		}

		return result;
	}

}