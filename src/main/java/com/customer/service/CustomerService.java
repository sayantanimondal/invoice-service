package com.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.converter.CustomerConverter;
import com.customer.dto.CustomerDTO;
import com.customer.model.Customer;
import com.customer.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerConverter converter;

	public CustomerDTO createOrUpdateCustomer(CustomerDTO customerDTO) {
		Optional<Customer> customerEntity = customerRepository.findById(customerDTO.getId());

		if (customerEntity.isPresent()) {
			Customer oldCustomer = customerEntity.get();
			oldCustomer.setCustomerName(customerDTO.getCustomerName());
			oldCustomer.setEmail(customerDTO.getEmail());
			oldCustomer.setMobile(customerDTO.getMobile());
			oldCustomer.setNationalId(customerDTO.getNationalId());
			oldCustomer = customerRepository.save(oldCustomer);
			CustomerDTO oldCustomerDTO = converter.mapCustomerEntityToCustomerDTO(oldCustomer);
			return oldCustomerDTO;
		} else {
			Customer newCustomer = new Customer();
			newCustomer.setCustomerName(customerDTO.getCustomerName());
			newCustomer.setEmail(customerDTO.getEmail());
			newCustomer.setMobile(customerDTO.getMobile());
			newCustomer.setNationalId(customerDTO.getNationalId());
			newCustomer = customerRepository.save(newCustomer);
			CustomerDTO newCustomerDTO = converter.mapCustomerEntityToCustomerDTO(newCustomer);

			return newCustomerDTO;
		}
	}

	public List<CustomerDTO> getAllCustomer() {
		Iterable<Customer> customerList = customerRepository.findAll();
		List<Customer> customerConverterToList = new ArrayList<Customer>();
		for (Customer customer : customerList) {
			customerConverterToList.add(customer);
		}
		return converter.mapListOfEntityToDTO(customerConverterToList);
	}

	public CustomerDTO getCustomerByID(int id) {
		Optional<Customer> customerEntity = customerRepository.findById(id);
		if (customerEntity.isPresent()) {
			CustomerDTO customerDTO = converter.mapCustomerEntityToCustomerDTO(customerEntity.get());
			return customerDTO;
		}
		return null;
	}

	public void deleteCustomerById(int id) {
		customerRepository.deleteById(id);
	}

}
