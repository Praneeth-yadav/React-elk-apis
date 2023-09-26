package com.ep.ecom_api;


import com.ep.ecom_api.entity.LoginDetails;
import com.ep.ecom_api.entity.Role;
import com.ep.ecom_api.repository.LoginDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableMongoRepositories
public class EcomApiApplication implements CommandLineRunner {

	@Autowired
	public LoginDetailsRepo loginDetailsRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EcomApiApplication.class, args);
		System.out.println("Creating documents");


	}

	@Override
	public void run(String... args) throws Exception {
		loginDetailsRepo.deleteAll();
		System.out.println("Inserting into DB");
		for(int i=0;i<4;i++) {
			LoginDetails loginDetails = new LoginDetails();
			loginDetails.setFirstName("John"+i);
			loginDetails.setLastName("Doe"+i);
			loginDetails.setEmail("john.doe"+i+"@example.com");
			loginDetails.setPassword(passwordEncoder.encode("password"+i));
			loginDetails.setRole(Role.USER); // Set the user's role

			// Save the LoginDetails object to the MongoDB collection
			loginDetailsRepo.save(loginDetails);
			System.out.println("Inserting into DB - Completed for = "+i);
		}
		List<LoginDetails> loginDetails1 =loginDetailsRepo.findAll();
		System.out.println(loginDetails1);

		Optional<LoginDetails> loginDetailsByEmail=loginDetailsRepo.findByEmail("john.doe1@example.com");
		System.out.println(loginDetailsByEmail);
	}
}
