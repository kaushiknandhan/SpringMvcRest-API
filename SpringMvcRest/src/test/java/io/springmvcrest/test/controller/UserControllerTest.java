package io.springmvcrest.test.controller;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import io.springmvcrest.controller.UserController;
import io.springmvcrest.entity.User;
import io.springmvcrest.service.UserService;
import io.springmvcrest.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class UserControllerTest {
	
	@Mock
	private UserService userService;
	@InjectMocks
	private UserController userController;
	
	private MockMvc mockMVC;
	
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
		
		mockMVC = MockMvcBuilders.standaloneSetup(userController).build();
	}
	@Test
	public void testFindAllUsers() throws Exception{
		mockMVC.perform(MockMvcRequestBuilders.get("/users"))
		.andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(userService).findAllUsers();
	}
	@Test
	public void findUser() throws Exception{
		mockMVC.perform(MockMvcRequestBuilders.get("/users/"+user.getUserId()))
		.andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(userService).findUserById(user.getUserId());
	}
	

}
