package com.sec.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig {
	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods(HttpMethod.POST.name())
				.allowedMethods(HttpMethod.GET.name())
				.allowedMethods(HttpMethod.PUT.name())
				.allowedMethods(HttpMethod.DELETE.name())
				.allowCredentials(false)
				.maxAge(3600);
			}
		};
	}
}