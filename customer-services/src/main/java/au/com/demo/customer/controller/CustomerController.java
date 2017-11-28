package au.com.demo.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import au.com.demo.customer.model.Customer;
import au.com.demo.customer.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	public static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/v1/customers", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> listAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		if (customers.isEmpty()) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	@RequestMapping(value = "/v1/customers", method = RequestMethod.POST)
	public ResponseEntity<?> insertCustomers(@RequestBody List<Customer> customers) {

		logger.info("inserting customers..");

		customerService.insertCustomers(customers);

		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/v1/customer/{lastName}", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> findCustomerByLastName(@PathVariable(required=true) String lastName) {

			List<Customer> customers = customerService.findByLastName(lastName);

			if (customers.isEmpty()) {
				return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
}