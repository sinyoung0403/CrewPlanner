package com.reactionservice.crewproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CrewProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrewProjectApplication.class, args);
	}

}
