package com.example.textsearchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TextSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TextSearchServiceApplication.class, args);
	}

}
