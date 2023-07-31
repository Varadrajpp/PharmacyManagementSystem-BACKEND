package com.casestudy.OrdersService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableFeignClients
//@OpenAPIDefinition(info= @Info(title="Pharmacy API'S"))
@OpenAPIDefinition(info = @Info(title = "Orders APIS", version = "2.0", description = "Orders Information"))
public class OrdersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersServiceApplication.class, args);
	}

}
