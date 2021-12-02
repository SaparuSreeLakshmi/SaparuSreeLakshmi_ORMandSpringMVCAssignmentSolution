package com.customermanagement.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.customermanagement.entities.Customers;

@Service
public interface CustServices {
	// CRUD functionalities
	// Method to Retrieve all customer records
	public List<Customers> findAll();

	public Customers findById(int id);

	public void save(Customers cust);

	public void deleteById(int id);

}
