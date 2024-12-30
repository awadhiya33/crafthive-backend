package com.example.crafthive.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.crafthive.entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, String> {

}
