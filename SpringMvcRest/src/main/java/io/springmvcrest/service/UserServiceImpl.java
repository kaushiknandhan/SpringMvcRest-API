package io.springmvcrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.springmvcrest.entity.User;
import io.springmvcrest.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Override
	public User createUser(User user) {
		
		User createdUser = userRepository.createUser(user);
		return createdUser;
	}
	@Override
	public User updateUser(String userId, User user) {
		User updatedUser = userRepository.updateUser(userId, user);
		return updatedUser;
	}
	

}
