package au.com.demo.customer.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import au.com.demo.customer.model.Customer;
import au.com.demo.customer.model.OrderDetails;
import au.com.demo.customer.repository.CustomerRepository;

@Component("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> getAllCustomers() {
		return (List<Customer>) this.customerRepository.findAll();
	}

	@Override
	public void saveCustomer(Customer customer) {
		this.customerRepository.save(customer);
	}

	@Override
	public boolean doesCustomerExist(Customer customer) {
		return false;
	}

	@Override
	public void insertCustomers(List<Customer> customers) {
		this.customerRepository.save(customers);
	}

	@Override
	public List<Customer> findByLastName(String lastName) {
		return this.customerRepository.findByLastName(lastName);
	}

	@Override
	public Customer findByID(long id) {
		return this.customerRepository.findOne(id);
	}
}
