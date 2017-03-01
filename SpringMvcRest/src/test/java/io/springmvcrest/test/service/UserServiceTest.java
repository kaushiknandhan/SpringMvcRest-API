package io.springmvcrest.test.service;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.springmvcrest.entity.User;
import io.springmvcrest.exception.NoUserPresent;
import io.springmvcrest.exception.UserAlreadyExists;
import io.springmvcrest.repository.UserRepository;
import io.springmvcrest.service.UserService;
import io.springmvcrest.service.UserServiceImpl;
import io.springmvcrest.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class UserServiceTest {
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserService userService = new UserServiceImpl();

	private User user;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setUserId(UUID.randomUUID().toString());
		user.setCity("chicago");
		user.setEmailId("chicago@gmail.com");
		user.setPassword("chicago");
		user.setZipcode(656555);
		user.setUserName("always Chicago");
	}

	@Test
	public void testFindAllUsers() {
		userService.findAllUsers();
		Mockito.verify(userRepository).findAllUsers();
	}
	@Test
	public void testFindUserById() throws NoUserPresent{
		Mockito.when(userRepository.findUserById(user.getUserId())).thenReturn(user);
		User actual = userService.findUserById(user.getUserId());
		Assert.assertEquals(user, actual);
		// Mockito.verify(userRepository).findUserById(user.getUserId());
		
	}
	@Test(expected=NoUserPresent.class)
	public void testFindUserByIdException() throws NoUserPresent{
		Mockito.when(userRepository.findUserById(user.getUserId())).thenReturn(null);
		User actual = userService.findUserById(user.getUserId());
		Assert.assertEquals(null, actual);
	}
	@Test
	public void testCreateuser() throws UserAlreadyExists{
		Mockito.when(userRepository.findUserBYEmail(user)).thenReturn(null);
		userService.createUser(user);
		Mockito.verify(userRepository).createUser(user);
	}
	@Test(expected=UserAlreadyExists.class)
	public void testCreateuserException() throws UserAlreadyExists{
		Mockito.when(userRepository.findUserBYEmail(user)).thenReturn(user);
		User actual = userService.createUser(user);
		Assert.assertEquals(user, actual);
	}

}
