package com.example.backend.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entity.Cart;
import com.example.backend.entity.User;



@Repository
public interface CartDao extends CrudRepository <Cart, Integer> {

	public List<Cart> findByUser(User user);
}
