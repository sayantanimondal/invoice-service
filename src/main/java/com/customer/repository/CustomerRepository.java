package com.customer.repository;

import org.springframework.data.repository.CrudRepository;

import com.customer.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
