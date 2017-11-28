package au.com.demo.customer.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import au.com.demo.customer.model.OrderDetails;

public interface OrderRepository extends PagingAndSortingRepository<OrderDetails, Long> {

}
