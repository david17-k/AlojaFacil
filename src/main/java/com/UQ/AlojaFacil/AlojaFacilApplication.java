package com.UQ.AlojaFacil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AlojaFacilApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlojaFacilApplication.class, args);
	}

}
