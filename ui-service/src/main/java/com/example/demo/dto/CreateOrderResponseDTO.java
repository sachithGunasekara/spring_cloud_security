package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

public class CreateOrderResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5506447748123312150L;
	private String status;
	private String message;
	private List<String> orderItems;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<String> orderItems) {
		this.orderItems = orderItems;
	}
}
