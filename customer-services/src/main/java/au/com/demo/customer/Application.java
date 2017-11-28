package au.com.demo.customer;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import au.com.demo.customer.model.Customer;
import au.com.demo.customer.model.OrderDetails;
import au.com.demo.customer.repository.CustomerRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * some sample data to begin with.
	 */
	@Override
	@Transactional
	public void run(String... strings) throws Exception {

		Customer customer1 = new Customer("Jack", "John");
		Set<OrderDetails> orderDetails1 = new HashSet<OrderDetails>() {
			{
				add(new OrderDetails(customer1, "order one"));
				add(new OrderDetails(customer1, "order two"));
			}
		};
		customer1.setOrderDetails(orderDetails1);

		Customer customer2 = new Customer("Jim", "Jo");
		Set<OrderDetails> orderDetails2 = new HashSet<OrderDetails>() {
			{
				add(new OrderDetails(customer2, "order one"));
				add(new OrderDetails(customer2, "order two"));
			}
		};
		customer2.setOrderDetails(orderDetails2);

		customerRepository.save(customer1);
		customerRepository.save(customer2);

	}
}
