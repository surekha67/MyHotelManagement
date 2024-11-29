package com.example.role.service;

import java.util.List;

import javax.management.relation.RoleNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.role.controller.RoleController;
import com.example.role.entity.Role;
import com.example.role.repository.RoleRepository;

@Service
public class RoleService {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    private final RoleRepository roleRepository;
    

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
    	logger.info("Fetching all roles: {}");
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) throws RoleNotFoundException {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with id: " + id));
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, Role role) {
        if (roleRepository.existsById(id)) {
            role.setId(id);
            return roleRepository.save(role);
        }
        return null;
    }

    public void deleteRole(Long id) throws RoleNotFoundException {
        if (!roleRepository.existsById(id)) {
            throw new RoleNotFoundException("Role not found with id: " + id);
        }
        roleRepository.deleteById(id);
}
}