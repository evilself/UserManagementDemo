package com.westernacher.demo.data.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.westernacher.model.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findById(Long id);
}