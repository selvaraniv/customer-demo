package au.com.demo.customer;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import au.com.demo.customer.model.Customer;
import au.com.demo.customer.model.OrderDetails;
import au.com.demo.customer.repository.CustomerRepository;


@SpringBootApplication
public class Application implements CommandLineRunner
{

	@Autowired
	CustomerRepository customerRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	/**
	 * some sample data to being with.
	 */
	@Override
    @Transactional
    public void run(String... strings) throws Exception {
		
		Customer customer1 = new Customer("Jack", "John");
		Set<OrderDetails> orderDetails = new HashSet<OrderDetails>(){{
			add(new OrderDetails(customer1, "order one"));
			add(new OrderDetails(customer1, "order two"));
		}};
		customer1.setOrders(orderDetails);
		customerRepository.save(customer1);
		
		}
	}
