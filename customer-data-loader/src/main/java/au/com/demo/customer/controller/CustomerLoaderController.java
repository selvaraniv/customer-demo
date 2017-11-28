package au.com.demo.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import au.com.demo.customer.data.CsvData;
import au.com.demo.customer.data.CsvReader;

@RestController
@RequestMapping("/api")
public class CustomerLoaderController {

	public static final Logger logger = LoggerFactory.getLogger(CustomerLoaderController.class);

	@RequestMapping(value = "/v1/customers", method = RequestMethod.GET)
	public ResponseEntity<List<CsvData>> loadAllCustomers() {
		
		String FILE_PATH = ".\\custom.csv";
		CsvReader reader = new CsvReader();
		List<CsvData> customerDetails = reader.readCsvFile(FILE_PATH); //no null checks performed or exceptions handled for now.
		if (customerDetails.isEmpty()) {
			return new ResponseEntity<List<CsvData>> (HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CsvData>>(customerDetails, HttpStatus.OK);
	}
}