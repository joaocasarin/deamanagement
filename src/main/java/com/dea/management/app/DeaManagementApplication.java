package com.dea.management.app;

import com.dea.management.app.user.domain.User;
import com.dea.management.app.user.repository.UserRepository;
import com.dea.management.app.user.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DeaManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DeaManagementApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void run(String... args) throws Exception {
		// this will delete all users in database before all operations
		this.userRepository.deleteAll();

		// this will create 3 users in database
		for (int i = 0; i < 3; i++) {
			User user = new User();
			user.setName("User " + i);
			user.setEmail("user" + i + "@gmail.com");
			user.setLinkedin("linkedin.com/user" + i);
			user.setPassword("password" + i);

			this.userRepository.save(user);
		}

		// this will list all users in database
		List<User> users = this.userService.findAllUsers();
		users.forEach(u -> System.out.println("Name: " + u.getName()));

		// get user using @Query
		Optional<User> loadedUserByName = this.userRepository.findByName("User 1");
		System.out.println("User 1 (@Query) name: " + loadedUserByName.get().getName());

		// get user using named query
		TypedQuery<User> query = this.entityManager.createNamedQuery("myQuery", User.class);
		query.setParameter("name", "User 2");
		User loadedUserByName2 = query.getSingleResult();
		System.out.println("User 2 (named query) name: " + loadedUserByName2.getName());

		// get user by email
		User loadedUserByEmail = this.userService.findUserByEmail("user2@gmail.com");
		System.out.println("User 2 (email) name: " + loadedUserByEmail.getName());

		// update user 2 linkedin
		loadedUserByEmail.setLinkedin("linkedin.com/user2-updated");
		this.userRepository.save(loadedUserByEmail);
	}
}
