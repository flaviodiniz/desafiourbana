package com.desafiourbana.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.desafiourbana.api.config.DesafioUrbanaApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(DesafioUrbanaApiProperty.class)
public class DesafioUrbana3Application {

	public static void main(String[] args) {
		SpringApplication.run(DesafioUrbana3Application.class, args);
	}

}
