package io.springmvcrest.test.repository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
import io.springmvcrest.repository.UserRepository;
import io.springmvcrest.repository.UserRepositoryImpl;
import io.springmvcrest.test.TestConfig;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class UserRepositoryTest {

	@Mock
	private EntityManager em;
	@Mock
	private TypedQuery<User> query;

	@InjectMocks
	private UserRepository userRepository = new UserRepositoryImpl();

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
		Mockito.when(em.createQuery("SELECT u FROM User u order by userName ASC", User.class)).thenReturn(query);
		userRepository.findAllUsers();
		Mockito.verify(query).getResultList();

	}

	@Test
	public void testFindUserById() {
		Mockito.when(em.find(User.class, user.getUserId())).thenReturn(user);
		User actual = userRepository.findUserById(user.getUserId());
		Assert.assertEquals(user, actual);

	}

	@Test
	public void testFindUserBYEmail() {
		List<User> users = Arrays.asList(user);
		Mockito.when(em.createQuery("SELECT u from User u where u.emailId = :pemail", User.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(users);
		User actual = userRepository.findUserBYEmail(user);
		Assert.assertEquals(users.get(0), actual);
	}

	@Test
	public void testFindUserBYEmailNull() {
		//user.setUserId("dfsdfsdf");
		Mockito.when(em.createQuery("SELECT u from User u where u.emailId = :pemail", User.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(null);
		User actual = userRepository.findUserBYEmail(user);
		Assert.assertEquals(null, actual);
	}

	@Test
	public void testCreateUser() {
		userRepository.createUser(user);
		Mockito.verify(em).persist(user);
	}

	@Test
	public void testUpdateUser() {
		userRepository.updateUser(user.getEmailId(), user);
		Mockito.verify(em).merge(user);
	}

	@Test
	public void testDeleteUser() {
		Mockito.when(userRepository.findUserById(user.getUserId())).thenReturn(user);
		userRepository.deleteUser(user.getUserId());
		Mockito.verify(em).remove(user);
	}

}
