package io.springmvcrest.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import io.springmvcrest.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
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
		User eixistingUser = getUserById(userId);
		if(eixistingUser != null){
			em.remove(eixistingUser);
		}
	}
		

	public User getUserById(String userId) {
		
		User getUser = em.find(User.class,userId );
		return getUser;
	}

}
