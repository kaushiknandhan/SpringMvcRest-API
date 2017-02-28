package io.springmvcrest.service;

import java.util.List;

import io.springmvcrest.entity.User;
import io.springmvcrest.exception.NoUserPresent;
import io.springmvcrest.exception.UserAlreadyExists;

public interface UserService {

	User createUser(User user) throws UserAlreadyExists;

	User updateUser(String userId, User user) throws NoUserPresent;

	void deleteUser(String userId) throws NoUserPresent;

	User findUserById(String userId) throws NoUserPresent;

	List<User> findAllUsers();

}
