package com.example.Blogapp;

import com.example.Blogapp.dao.UserRepo;
import com.example.Blogapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages={"com.example.Blogapp.service","com.example.Blogapp.controller"})
public class BlogappApplication {//implements CommandLineRunner {

//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	@Autowired
//	private UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogappApplication.class, args);
	}
//
//	@Override
//	public void run(String... args) throws Exception {
		//data fetches from entity password and encode
		//This would retrieve the plaintext password from the user with id 1 in the database,
		// using the userRepository, and then encode it into a hashed password, which is then
		// printed to the console.
		//Note: The above example is just a simple one, it is always recommended to use
		// repository function or any other way which is secure to fetch the data from database and also handle the exceptions.
//		User user = userRepo.findById(1).get();
//		String email = user.getEmail();
//		String plaintextPassword = userRepo.findByEmail(email).get().getPassword();
//		System.out.println(passwordEncoder.encode(plaintextPassword));
	}

