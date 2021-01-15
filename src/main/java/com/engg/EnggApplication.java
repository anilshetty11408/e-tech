package com.engg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EnggApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnggApplication.class, args);
		System.out.println("hello");
	}

}
