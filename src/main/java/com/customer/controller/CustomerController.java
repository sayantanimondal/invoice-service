package com.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.customer.dto.CustomerDTO;
import com.customer.dto.PolicyDTO;
import com.customer.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	RestTemplate restTemplate;
	

	@PostMapping
	public ResponseEntity<CustomerDTO> createOrUpdateCustomer(@RequestBody CustomerDTO customerDTO) {

		CustomerDTO customerDTO2 = customerService.createOrUpdateCustomer(customerDTO);
		return new ResponseEntity<CustomerDTO>(customerDTO2, HttpStatus.OK);
	}

	@GetMapping(value = "/getCustomer")
	public List<CustomerDTO> getAllCustomer() {
		List<CustomerDTO> customerList = customerService.getAllCustomer();
		return customerList;

	}

	@GetMapping(value = "/getCustomer/{id}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable int id) {
		CustomerDTO customerDTO = customerService.getCustomerByID(id);
		return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
		customerService.deleteCustomerById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/getAllPolicyFromPolicyMS")
	public List<PolicyDTO> getAllPoliciesFromPolicyMicroservice() {
		return (List<PolicyDTO>) restTemplate.getForObject(
				"http://policy-command-service/policy/getPolicy", 
				Object.class
				);
	}
	
}
