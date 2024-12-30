package com.example.crafthive.service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.crafthive.configuration.JwtRequestFilter;
import com.example.crafthive.dao.CartDao;
import com.example.crafthive.dao.ProductDao;
import com.example.crafthive.dao.UserDao;
import com.example.crafthive.entity.Cart;
import com.example.crafthive.entity.Product;
import com.example.crafthive.entity.User;

@Service 
public class ProductService {
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CartDao cartDao;
	
	public Product addNewProduct(Product product) {
		return productDao.save(product);
	}
	
	public List<Product> getAllProducts(int pageNumber,String searchKey){
		Pageable pageable = PageRequest.of(pageNumber,12);
		if(searchKey.equals("")) {
			return (List<Product>)productDao.findAll(pageable);
		}else {
			return (List<Product>)productDao.findByProductNameContainingIgnoreCaseOrProductDescContainingIgnoreCase(
					searchKey,searchKey,pageable);
		}
	
		
	}
	public void deleteProductDetails(int productId) {
		productDao.deleteById(productId);
	}
	public Product getProductDetailsById(int productId) {
		return productDao.findById(productId).get();
	}
	public List<Product> getProductDetails(boolean isSingleProductCheckout,int productId) {
		if(isSingleProductCheckout && productId != 0) {
			List<Product> list=new ArrayList<>();
			Product product=productDao.findById(productId).get();
			list.add(product);
			return list;
		}
		else {
			//we are going to implement entire cart items
			
			String username = JwtRequestFilter.CURRENT_USER;

           User user = userDao.findById(username).get();
          List<Cart> carts =  cartDao.findByUser(user);
          
          return carts.stream().map(x -> x.getProduct()).collect(Collectors.toList());
			
		}
		
	}
}
