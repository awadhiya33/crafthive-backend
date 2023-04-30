package com.example.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.configuration.JwtRequestFilter;
import com.example.backend.dao.CartDao;
import com.example.backend.dao.OrderDetailDao;
import com.example.backend.dao.ProductDao;
import com.example.backend.dao.UserDao;
import com.example.backend.entity.Cart;
import com.example.backend.entity.OrderDetail;
import com.example.backend.entity.OrderInput;
import com.example.backend.entity.OrderProductQuantity;
import com.example.backend.entity.Product;
import com.example.backend.entity.TransactionDetails;
import com.example.backend.entity.User;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class OrderDetailService {
	
	private static final String ORDER_PLACED="placed";
	private static final String KEY="rzp_test_xoG4aZoVYym0HF";
	private static final String SECRET_KEY="6RkR1ujhqzqWukKOnmUgQB3x";
	private static final String CURRENCY="INR";
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired 
	private CartDao cartDao;
	
	public List<OrderDetail> getAllOrderDetails(String status) {
	List<OrderDetail> orderDetails = new ArrayList<>();	
	
	if(status.equals("All")) {
	 orderDetailDao.findAll().forEach(
			x -> orderDetails.add(x)
			); 	
	
	}else 
	{
		orderDetailDao.findByOrderStatus(status).forEach(
				x -> orderDetails.add(x) 
				);
	}
	return orderDetails;
	}
	
	
	
	public List<OrderDetail> getOrderDetails() {
		String currentUser = JwtRequestFilter.CURRENT_USER;
		User user = userDao.findById(currentUser).get();
		//System.out.println(orderDetailDao.findByUser(user)+"dd");
		return orderDetailDao.findByUser(user);
	}
	
	
	public void placeOrder(OrderInput orderInput,Boolean isSingleProductCheckout) {
		List<OrderProductQuantity> productQuantityList=orderInput.getOrderProductQuantityList();
		
		for(OrderProductQuantity o:productQuantityList) {
			
			Product product=productDao.findById(o.getProductId()).get();
			
			String currentUser =JwtRequestFilter.CURRENT_USER;
			User user=userDao.findById(currentUser).get();
			
			OrderDetail orderDetail=new OrderDetail(
					orderInput.getFullName(),
					orderInput.getFullAddress(),
					orderInput.getContactNumber(),
					orderInput.getAlternatenumber(),
					ORDER_PLACED,
					product.getProductDiscountedPrice() * o.getProductQuantity(),
					product,user,
					orderInput.getTransactionId());
			
			
			if(!isSingleProductCheckout) {
				List<Cart> carts = cartDao.findByUser(user);
				carts.stream().forEach(x -> cartDao.deleteById(x.getCartId()));
			}
			
			
			
			orderDetailDao.save(orderDetail);
		}
		
		
	}
	
	public void markOrderAsDelivered(int orderId) {
		OrderDetail orderDetail = orderDetailDao.findById(orderId).get();
		
		if(orderDetail != null) {
			orderDetail.setOrderStatus("Delivered");
			orderDetailDao.save(orderDetail);
		}
		
	}
	public TransactionDetails createTransaction(double amount) {
		try {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("amount", (amount*100));
			jsonObject.put("currency",CURRENCY );
			RazorpayClient razorpayClient =new RazorpayClient(KEY,SECRET_KEY);
			Order order= razorpayClient.orders.create(jsonObject);
			return prepareTransactionDetails(order);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
		return null;
	}
	
	private TransactionDetails prepareTransactionDetails(Order order) {
		String orderId=order.get("id");
		String currency=order.get("currency");
		int amount=order.get("amount");
		
		TransactionDetails t=new TransactionDetails(orderId,currency,amount,KEY);
		return t;
	}
}
