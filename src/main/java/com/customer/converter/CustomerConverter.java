package com.customer.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.customer.dto.CustomerDTO;
import com.customer.model.Customer;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class CustomerConverter {
	
	private static MapperFacade mapper;

	
	public CustomerConverter() {
		final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		
		mapperFactory
		.classMap(Customer.class, CustomerDTO.class)
		.field("id", "id")
		.field("nationalId", "nationalId")
		.field("mobile", "mobile")
		.field("email", "email")
		.byDefault()
		.register();
		
		mapper = mapperFactory.getMapperFacade();

	}
	
	public CustomerDTO mapCustomerEntityToCustomerDTO(final Customer customer) {
		CustomerDTO customerDTO = mapper.map(customer, CustomerDTO.class);
		return customerDTO;
	}
	
	public List<CustomerDTO> mapListOfEntityToDTO(final List<Customer> customerList) {
		return customerList
				.stream()
				.map(this::mapCustomerEntityToCustomerDTO )
				.collect(Collectors.toList());
	}

}
