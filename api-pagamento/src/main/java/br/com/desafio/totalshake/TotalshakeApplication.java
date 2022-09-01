package br.com.desafio.totalshake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TotalshakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TotalshakeApplication.class, args);
	}

}
