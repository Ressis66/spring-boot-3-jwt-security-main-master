package com.vaganov.task;

import com.vaganov.task.auth.AuthenticationService;
import com.vaganov.task.auth.RegisterRequest;
import com.vaganov.task.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.name("User")
					.username("User")
					.email("user@mail.com")
					.password("password")
					.role(Role.USER)
					.build();
			System.out.println("User token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.name("Admin")
					.username("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(Role.ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(manager).getAccessToken());

			var task = RegisterRequest.builder()
					.name("Task")
					.username("Task")
					.email("task@mail.com")
					.password("password")
					.role(Role.TASKS)
					.build();
			System.out.println("Task token: " + service.register(task).getAccessToken());



		};
	}
}
