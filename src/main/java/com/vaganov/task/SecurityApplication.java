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

			var album = RegisterRequest.builder()
					.name("Album")
					.username("Album")
					.email("album@mail.com")
					.password("password")
					.role(Role.ALBUMS)
					.build();
			System.out.println("Album token: " + service.register(album).getAccessToken());

			var post = RegisterRequest.builder()
					.name("Post")
					.username("Post")
					.email("post@mail.com")
					.password("password")
					.role(Role.POSTS)
					.build();
			System.out.println("Post token: " + service.register(post).getAccessToken());
		};
	}
}
