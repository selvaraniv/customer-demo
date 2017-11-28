package au.com.demo.customer.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = -2808989785042828146L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	// MySQL doesn't like order as the entity name.
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<OrderDetails> orderDetails;

	public Customer() {
	}

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Customer(String firstName, String lastName, Set<OrderDetails> orders) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.orderDetails = orders;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public Set<OrderDetails> getOrders() {
		return this.orderDetails;
	}

	public void setOrders(Set<OrderDetails> orders) {
		this.orderDetails = orders;
	}

	public void addOrder(OrderDetails order) {
		getOrders().add(order);
	}

}
