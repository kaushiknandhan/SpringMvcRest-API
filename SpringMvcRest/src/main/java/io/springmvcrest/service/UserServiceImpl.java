package io.springmvcrest.service;

import java.util.List;

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
	@Override
	public void deleteUser(String userId) {
		
		userRepository.deleteUser(userId);
	}
	@Override
	public User findUserById(String userId) {
		User existingUser = userRepository.findUserById(userId);
		return existingUser;

	}
	@Override
	public List<User> findAllUsers() {
		List<User> userList = userRepository.findAllUsers();
		return userList;
	}
	

}
