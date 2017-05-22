package com.westernacher.demo.services;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.westernacher.demo.data.interfaces.UserRepository;
import com.westernacher.model.domain.User;

@Service
public class UserServiceImpl implements UserService {	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Inject
	private UserRepository userRepo;	

	@Override
	@Transactional
	public User saveUser(User user) {
		LOGGER.info("Saving user... {} {}", user.getFirstName(), user.getLastName());		
		return userRepo.saveAndFlush(user);		
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getAllUsers() {
		return userRepo.findAll(); //TODO add sort
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> getUserByUserId(Long id) {
		return userRepo.findById(id);
	}	

	@Override
	@Transactional
	public void deleteUser(Long id) {
		LOGGER.info("Deleting user with id:{}", id);
		userRepo.delete(id);		
	}
}