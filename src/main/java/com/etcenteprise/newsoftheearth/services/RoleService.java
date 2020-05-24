package com.etcenteprise.newsoftheearth.services;

import com.etcenteprise.newsoftheearth.entities.Role;

import java.util.List;

public interface RoleService {
    void saveRole(Role role);
    List<Role> findAllRole();
}
