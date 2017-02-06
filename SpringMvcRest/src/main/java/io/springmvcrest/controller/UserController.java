package io.springmvcrest.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.springmvcrest.entity.User;
import io.springmvcrest.service.UserService;

@RestController
@RequestMapping(path="/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User createUser(@RequestBody User user){
	
		User createdUser = userService.createUser(user);
		
		return createdUser;
	}
	@RequestMapping(path = "{id}",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User updateUser(@PathVariable(value="id") String userId, @RequestBody User user){
		User updatedUser = userService.updateUser(userId, user); 
		return updatedUser;
	}
	
	@RequestMapping(path="{id}",method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable(value="id") String userId){
		
		userService.deleteUser(userId);
	}
}
