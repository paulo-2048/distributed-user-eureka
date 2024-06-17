package br.ucsal.dfs_app_c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DfsAppCApplication {

	public static void main(String[] args) {
		SpringApplication.run(DfsAppCApplication.class, args);
	}

}
