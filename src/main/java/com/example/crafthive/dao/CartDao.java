package com.example.crafthive.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.crafthive.entity.Cart;
import com.example.crafthive.entity.User;



@Repository
public interface CartDao extends CrudRepository <Cart, Integer> {

	public List<Cart> findByUser(User user);
}
