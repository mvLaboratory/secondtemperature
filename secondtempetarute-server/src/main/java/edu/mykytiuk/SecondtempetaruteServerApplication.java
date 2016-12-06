package edu.mykytiuk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SecondtempetaruteServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondtempetaruteServerApplication.class, args);
	}
}
