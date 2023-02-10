package com.onlineshop;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Warehouse Microservice", version = "1.0.0"),
//		servers = {@Server(url = "http://localhost:8080"), @Server(url = "http://example.com")},
		tags = {@Tag(name = "Warehouse", description = "This is Warehouse Microservice End Points ")}
)
class Application{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
