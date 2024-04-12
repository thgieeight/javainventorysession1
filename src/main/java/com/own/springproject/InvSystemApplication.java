package com.own.springproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Inventory REST API", version="4.0", description = "inventory CRUD APIS"))

public class InvSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvSystemApplication.class, args);
	}

}
