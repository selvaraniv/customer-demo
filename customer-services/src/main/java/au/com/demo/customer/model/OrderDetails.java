package au.com.demo.customer.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class OrderDetails implements Serializable {

	private static final long serialVersionUID = -2990078161411395604L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(nullable=false)
	private Customer customer;
	
	@Column(nullable = false)
	private String orderName;
	
	public OrderDetails(){
	}
	
	public OrderDetails(String name){
		this.orderName = name;
	}
	
	public OrderDetails(Customer customer, String name){
		this.customer =  customer;
		this.orderName = name;
	}
	
	@JsonBackReference
	public Customer getCustomer(){
		return this.customer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
}
