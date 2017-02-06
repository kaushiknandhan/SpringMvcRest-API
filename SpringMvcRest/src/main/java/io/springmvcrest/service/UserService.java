package io.springmvcrest.service;

import java.util.List;

import io.springmvcrest.entity.User;

public interface UserService {

	User createUser(User user);

	User updateUser(String userId, User user);

	void deleteUser(String userId);

	User findUserById(String userId);

	List<User> findAllUsers();

}
