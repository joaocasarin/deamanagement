package com.dea.management.app;

import com.dea.management.app.student.domain.Student;
import com.dea.management.app.student.repository.StudentRepository;
import com.dea.management.app.user.domain.User;
import com.dea.management.app.user.repository.UserRepository;
import com.dea.management.app.user.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class DeaManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DeaManagementApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StudentRepository studentRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void run(String... args) throws Exception {
		// this will delete all users in database before all operations
		this.userRepository.deleteAll();

		// this will create 3 users in database
		for (int i = 0; i < 100; i++) {
			User user = new User();
			user.setName("User " + i);
			user.setEmail("user" + i + "@gmail.com");
			user.setLinkedin("linkedin.com/user" + i);
			user.setPassword("password" + i);

			Student student = new Student();
			student.setUniversity("UNI" + i);
			student.setGraduation("GRAD" + i);
			student.setFinishDate(LocalDate.now());
			student.setUser(user);

			this.studentRepository.save(student);
		}
	}
}
