package com.example.crafthive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.crafthive.entity.Role;
import com.example.crafthive.service.RoleService;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

// vijay

    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }
}
