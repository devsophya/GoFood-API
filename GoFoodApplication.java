package br.com.gofood.gofood;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@OpenAPIDefinition(info = @Info(title = "GoFood", description = "API responsible for the GoFood application.", version = "1"))
@SpringBootApplication(scanBasePackages = "br.com.gofood.gofood")
public class GoFoodApplication {
	public static void main(String[] args) {
		SpringApplication.run(GoFoodApplication.class, args);
	}
}
//S