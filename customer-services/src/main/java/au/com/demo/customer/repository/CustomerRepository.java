package au.com.demo.customer.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import au.com.demo.customer.model.Customer;


public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
	
	List<Customer> findByLastName(String lastName);
}
