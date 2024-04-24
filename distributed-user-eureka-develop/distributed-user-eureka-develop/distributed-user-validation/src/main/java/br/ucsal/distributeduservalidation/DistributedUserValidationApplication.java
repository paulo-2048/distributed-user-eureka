package br.ucsal.distributeduservalidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DistributedUserValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedUserValidationApplication.class, args);
	}

}
