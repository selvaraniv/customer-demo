package au.com.demo.customer.service;

import java.util.List;

import au.com.demo.customer.model.Customer;
import au.com.demo.customer.model.OrderDetails;

public interface CustomerService {

	public List<Customer> getAllCustomers();

	public void saveCustomer(Customer customer);

	public boolean doesCustomerExist(Customer customer);

	public void insertCustomers(List<Customer> customers);
	
	public List<Customer> findByLastName(String lastName);
	
	public Customer findByID(long id);
	
}
