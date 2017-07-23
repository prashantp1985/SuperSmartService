package com.dhl.chatbot.entities;
import java.io.Serializable;

//import org.hibernate.Hibernate;


/**
 * 
 * @author Prashant Padmanabhan
 *
 */
@SuppressWarnings("serial")
public class OrderDetails implements Serializable, Cloneable {

	private String orderNo = null;
	private String otherDetails = null;
	private String orderStatus = null;
	
	
	
	public OrderDetails() {
		super();
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOtherDetails() {
		return otherDetails;
	}
	public void setOtherDetails(String orderDetails) {
		this.otherDetails = orderDetails;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
}
