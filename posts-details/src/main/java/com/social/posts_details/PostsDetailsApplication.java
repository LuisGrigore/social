package com.social.posts_details;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PostsDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostsDetailsApplication.class, args);
	}

}
