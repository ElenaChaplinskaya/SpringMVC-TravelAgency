package com.project.tvavelAgency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TvavelAgencyApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context =
				SpringApplication.run(TvavelAgencyApplication.class, args);
	PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
			System.out.println(encoder.encode("pass"));
	}

}
