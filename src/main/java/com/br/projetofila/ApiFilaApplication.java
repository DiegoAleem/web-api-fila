package com.br.projetofila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiFilaApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ApiFilaApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ApiFilaApplication.class);
	}
	
	@RequestMapping(value="/")
	public String home() {
		return "Funciona!";
	}
	
}
