package com.dea.management.app;

import com.dea.management.app.student.domain.Student;
import com.dea.management.app.student.repository.StudentRepository;
import com.dea.management.app.user.domain.User;
import com.dea.management.app.user.repository.UserRepository;
import com.dea.management.app.user.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Dea Management", version = "1.0", description = "Dea Management API Description"),
		servers = {
				@Server(url = "http://localhost:8082${server.servlet.contextPath}", description = "Local environment URL"),
				@Server(url = "https://deamanagement.com.br${server.servlet.contextPath}", description = "Development environment URL")
		}
)
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
		// this.userRepository.deleteAll();

		// this will create 3 users in database
		for (int i = 0; i < 100; i++) {
			User user = User.builder()
					.name("User " + i)
					.email("user" + i + "@gmail.com")
					.linkedin("linkedin.com/user" + i)
					.password("password" + i)
					.build();

			Student student = Student.builder()
					.university("UNI" + i)
					.graduation("GRAD" + i)
					.finishDate(LocalDate.now())
					.user(user)
					.build();

			// this.studentRepository.save(student);
		}
	}
}
