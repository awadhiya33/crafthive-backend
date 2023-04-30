package com.example.backend.entity;

import java.util.List;

public class OrderInput {
	
	private String fullName;
	private String fullAddress;
	private String contactNumber;
	private String alternatenumber;
	private String transactionId;
	private List<OrderProductQuantity> orderProductQuantityList;
	public OrderInput() {
		
	}
	
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAlternatenumber() {
		return alternatenumber;
	}
	public void setAlternatenumber(String alternatenumber) {
		this.alternatenumber = alternatenumber;
	}
	public List<OrderProductQuantity> getOrderProductQuantityList() {
		return orderProductQuantityList;
	}
	public void setOrderProductQuantityList(List<OrderProductQuantity> orderProductQuantityList) {
		this.orderProductQuantityList = orderProductQuantityList;
	}
	

}
