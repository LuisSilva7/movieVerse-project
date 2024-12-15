package org.movieverse.movieverse_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class MovieVerseBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieVerseBackendApplication.class, args);
	}

}
