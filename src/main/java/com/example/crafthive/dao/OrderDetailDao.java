package com.example.crafthive.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crafthive.entity.OrderDetail;
import com.example.crafthive.entity.User;

@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetail, Integer> {

	public List<OrderDetail> findByUser(User user);
	
	public List<OrderDetail> findByOrderStatus(String status);
}
