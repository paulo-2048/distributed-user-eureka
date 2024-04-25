package br.ucsal.distributeduserprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DistributedUserProfileApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedUserProfileApplication.class, args);
		
	}

}
