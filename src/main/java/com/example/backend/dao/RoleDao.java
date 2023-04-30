package com.example.backend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, String> {

}
