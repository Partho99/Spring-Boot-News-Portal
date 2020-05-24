package com.etcenteprise.newsoftheearth.services;

import com.etcenteprise.newsoftheearth.entities.Role;
import com.etcenteprise.newsoftheearth.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void saveRole(Role role) {
       roleRepository.save(role);
    }

    @Override
    public List<Role> findAllRole() {
       return roleRepository.findAll();
    }
}
