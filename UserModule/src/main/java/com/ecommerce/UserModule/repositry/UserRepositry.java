package com.ecommerce.UserModule.repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.UserModule.entity.User;

@Repository
public interface UserRepositry extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
}
