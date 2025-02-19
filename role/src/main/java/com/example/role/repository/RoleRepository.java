package com.example.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.role.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
