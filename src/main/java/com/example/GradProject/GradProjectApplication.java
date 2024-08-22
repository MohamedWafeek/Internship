package com.example.GradProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EntityScan("com.example.GradProject.*")
@EnableJpaRepositories("com.example.GradProject.*")
public class GradProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradProjectApplication.class, args);
	}

}
