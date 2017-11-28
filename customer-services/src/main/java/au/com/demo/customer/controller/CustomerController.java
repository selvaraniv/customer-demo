package au.com.demo.customer.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import au.com.demo.customer.model.Customer;
import au.com.demo.customer.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService customerService;
	@Autowired
	RestTemplate restTemplate;

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
	public ResponseEntity<List<Customer>> findCustomerByLastName(@PathVariable(required = true) String lastName) {

		List<Customer> customers = customerService.findByLastName(lastName);

		if (customers.isEmpty()) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	@RequestMapping(value = "/v1/customers/load", method = RequestMethod.POST)
	public ResponseEntity<?> loadCustomers() {

		logger.info("loading customers..");

		// set timeout.
		restTemplate = new RestTemplate(getClientHttpRequestFactory());
		String url = "http://localhost:8080/api/v1/customers";
		HttpEntity<List<Customer>> request = new HttpEntity<>(new ArrayList<Customer>());

		// Fire and populate the list.
		ParameterizedTypeReference<List<Customer>> responseType = new ParameterizedTypeReference<List<Customer>>() {
		};
		ResponseEntity<List<Customer>> resp = restTemplate.exchange(url, HttpMethod.GET, request, responseType);
		List<Customer> list = resp.getBody();

		// save
		try {

			logger.info("loading customer data of size= " + list.size());

			customerService.insertCustomers(list);

			logger.info("customer data successfully loaded");

			// return the full list for confirmation. Not an obvious choice for
			// performance but purely for testing.
			List<Customer> customers = customerService.getAllCustomers();
			if (customers.isEmpty()) {
				return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<String>(HttpStatus.I_AM_A_TEAPOT);
	}

	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		int timeout = 5000;
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(timeout);
		return clientHttpRequestFactory;
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}