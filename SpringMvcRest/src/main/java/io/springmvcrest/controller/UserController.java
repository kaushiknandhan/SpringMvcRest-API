package io.springmvcrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.springmvcrest.entity.User;
import io.springmvcrest.exception.NoUserPresent;
import io.springmvcrest.exception.UserAlreadyExists;
import io.springmvcrest.service.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User createUser(@RequestBody User user) throws UserAlreadyExists {

		User createdUser = userService.createUser(user);
		return createdUser;
	}

	@RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User updateUser(@PathVariable(value = "id") String userId, @RequestBody User user) throws NoUserPresent {
		User updatedUser = userService.updateUser(userId, user);
		return updatedUser;
	}

	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable(value = "id") String userId) throws NoUserPresent {

		userService.deleteUser(userId);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User findUser(@PathVariable(value = "id") String userId) throws NoUserPresent {
		User existingUser = userService.findUserById(userId);
		return existingUser;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<User> findAllUsers() {
		List<User> userList = userService.findAllUsers();
		return userList;
	}

}
