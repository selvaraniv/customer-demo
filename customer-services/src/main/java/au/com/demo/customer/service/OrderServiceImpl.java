package au.com.demo.customer.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import au.com.demo.customer.model.OrderDetails;
import au.com.demo.customer.repository.OrderRepository;

@Component("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public void addOrder(OrderDetails order) {
		this.orderRepository.save(order);
	}

}
