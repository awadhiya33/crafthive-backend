package com.example.crafthive.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.crafthive.entity.Product;



@Repository
public interface ProductDao extends CrudRepository<Product, Integer>{
	
     public List<Product> findAll(Pageable pageable);
	



	public List<Product> findByProductNameContainingIgnoreCaseOrProductDescContainingIgnoreCase(String searchKey,
			String searchKey2, Pageable pageable);

}
