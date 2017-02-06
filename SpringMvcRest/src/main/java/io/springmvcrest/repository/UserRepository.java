package io.springmvcrest.repository;

import java.util.List;

import io.springmvcrest.entity.User;

public interface UserRepository {

	User createUser(User user);

	User updateUser(String userId, User user);

	void deleteUser(String userId);

	User findUserById(String userId);

	List<User> findAllUsers();

	User findUserBYEmail(User user);

}
