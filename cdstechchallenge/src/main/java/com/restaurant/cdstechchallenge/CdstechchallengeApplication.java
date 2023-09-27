package com.restaurant.cdstechchallenge;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Random;

@SpringBootApplication
public class CdstechchallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CdstechchallengeApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper()	{
		return new ModelMapper();
	}

	@Lazy
	@Bean
	public Random random()	{
		return new Random();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
						.allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
						.allowedOrigins("*");
			}
		};
	}
}
