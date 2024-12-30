package com.example.crafthive.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.crafthive.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, String> {
}
