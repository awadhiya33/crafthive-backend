package com.example.backend.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.OrderDetail;
import com.example.backend.entity.OrderInput;
import com.example.backend.entity.TransactionDetails;
import com.example.backend.service.OrderDetailService;

@RestController
public class OrderDetailsController {
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@PreAuthorize("hasRole('User')")
	@PostMapping({"/placeOrder/{isSingleProductCheckout}"})
	public void placeOrder(@PathVariable(name = "isSingleProductCheckout") boolean isSingleProductCheckout,
	@RequestBody OrderInput orderInput) {
		orderDetailService.placeOrder(orderInput,isSingleProductCheckout);
	}

	@PreAuthorize("hasRole('User')")
	@GetMapping({"/getOrderDetails"})
	public List<OrderDetail> getOrderDetails() {
		return orderDetailService.getOrderDetails();
	}
	
	@PreAuthorize("hasRole('Admin')") 
	@GetMapping({"/getAllOrderDetails/{status}"})
	public List<OrderDetail> getAllOrderDetails(@PathVariable String status) {
		return orderDetailService.getAllOrderDetails(status);
	}
	
	@PreAuthorize("hasRole('Admin')") 
	@GetMapping({"/markOrderAsDelivered/{orderId}"}) 
	public void markOrderAsDelivered(@PathVariable(name ="orderId")int orderId) {
	    orderDetailService.markOrderAsDelivered(orderId);
		}
	@PreAuthorize("hasRole('User')") 
	@GetMapping("/createTransaction/{amount}")
	public TransactionDetails createTransaction(@PathVariable double amount) {
		return orderDetailService.createTransaction(amount);
	}
	
	
}
