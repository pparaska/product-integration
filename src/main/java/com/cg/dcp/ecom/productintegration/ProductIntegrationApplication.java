package com.cg.dcp.ecom.productintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.cg.dcp")
public class ProductIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductIntegrationApplication.class, args);
	}

}
