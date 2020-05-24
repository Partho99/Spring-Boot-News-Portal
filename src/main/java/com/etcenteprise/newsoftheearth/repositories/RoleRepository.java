package com.etcenteprise.newsoftheearth.repositories;

import com.etcenteprise.newsoftheearth.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
