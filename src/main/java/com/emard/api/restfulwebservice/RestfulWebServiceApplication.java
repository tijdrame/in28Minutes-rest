package com.emard.api.restfulwebservice;

import java.time.LocalDate;

import com.emard.api.restfulwebservice.response.Post;
import com.emard.api.restfulwebservice.response.PostRepository;
import com.emard.api.restfulwebservice.response.User;
import com.emard.api.restfulwebservice.response.UserRepository;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class RestfulWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServiceApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(UserRepository repository, PostRepository postRepository){
		return args -> {
			User u1 = new User();u1.setBirthDate(LocalDate.now().minusYears(9));u1.setName("Tij");
			User u2 = new User();u2.setBirthDate(LocalDate.now().minusYears(9));u2.setName("Tij");
			User u3 = new User();u3.setBirthDate(LocalDate.now().minusYears(9));u3.setName("Babs");
			User u4 = new User();u4.setBirthDate(LocalDate.now().minusYears(9));u4.setName("Saly");
			
			Post p = new Post();p.setDescription("First post");//p.setUser(u1);
			Post p1 = new Post();;p1.setDescription("Second post");//p1.setUser(u1);
			Post p2 = new Post();p2.setDescription("Third post");//p2.setUser(u2);
			
			u1.getPosts().add(p);
			u1.getPosts().add(p1);
			u2.getPosts().add(p2);

			repository.save(u1);
			repository.save(u2);
			repository.save(u3);
			repository.save(u4);
			p.setUser(u1);p1.setUser(u1);p2.setUser(u2);
			postRepository.save(p);
			postRepository.save(p1);
			postRepository.save(p2);
			
		};
	}
}
