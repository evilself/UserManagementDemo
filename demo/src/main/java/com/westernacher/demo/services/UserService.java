package com.westernacher.demo.services;

import java.util.List;
import java.util.Optional;

import com.westernacher.model.domain.User;

public interface UserService {	
	User saveUser(User user);
	List<User> getAllUsers();
	Optional<User> getUserByUserId(Long id);
	void deleteUser(Long id);
}