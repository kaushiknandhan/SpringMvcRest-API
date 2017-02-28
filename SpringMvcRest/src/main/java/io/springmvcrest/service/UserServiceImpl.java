package io.springmvcrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.springmvcrest.entity.User;
import io.springmvcrest.exception.NoUserPresent;
import io.springmvcrest.exception.UserAlreadyExists;
import io.springmvcrest.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User createUser(User user) throws UserAlreadyExists {
		User existingUser = userRepository.findUserBYEmail(user);
		if(existingUser == null){
			User createdUser = userRepository.createUser(user);
			return createdUser;
		}
		else{
			throw new UserAlreadyExists("User already present with emailId");
		}
		
	}
	@Override
	public User updateUser(String userId, User user) throws NoUserPresent {
		User existingUser = userRepository.findUserById(userId);
		if(existingUser != null){
			User updatedUser = userRepository.updateUser(userId, user);
			return updatedUser;
		}	else{
			throw new NoUserPresent("No user present with the given id"+userId);
		}
		
	}
	@Override
	public void deleteUser(String userId) throws NoUserPresent {
		User existingUser = userRepository.findUserById(userId);
		if(existingUser != null){
			userRepository.deleteUser(userId);
		}	else{
			throw new NoUserPresent("No user present with the given id"+userId);
		}
		
	}
	@Override
	public User findUserById(String userId) throws NoUserPresent {
		User existingUser = userRepository.findUserById(userId);
		if(existingUser != null){
			return existingUser;
		}	else{
			throw new NoUserPresent("No user present with the given id"+userId);
		}
		

	}
	@Override
	public List<User> findAllUsers() {
		List<User> userList = userRepository.findAllUsers();
		return userList;
	}
	

}
