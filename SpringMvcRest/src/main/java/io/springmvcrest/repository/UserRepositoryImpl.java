package io.springmvcrest.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import io.springmvcrest.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User createUser(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public User updateUser(String userId, User user) {
		em.merge(user);
		return user;
	}

	@Override
	public void deleteUser(String userId) {
		System.out.println(userId);
		User eixistingUser = findUserById(userId);
		if (eixistingUser != null) {
			em.remove(eixistingUser);
		}
	}

	public User findUserById(String userId) {

		User getUser = em.find(User.class, userId);
		return getUser;
	}

	@Override
	public List<User> findAllUsers() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u order by userName ASC", User.class);
		List<User> existingUserList = query.getResultList();
		return existingUserList;

	}

	@Override
	public User findUserBYEmail(User user) {
		TypedQuery<User> query = em.createQuery("SELECT u from User u where u.emailId = :pemail", User.class);
		query.setParameter("pemail", user.getEmailId());
		List<User> existingUserList = query.getResultList();
		if (existingUserList.isEmpty()) {
			return null;
		} else {
			return existingUserList.get(0);
		}
	}

}
