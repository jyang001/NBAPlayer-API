package com.yang.nbaplayerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class NbaPlayerApiApplication {

	@Bean
	public RestTemplate getRestTempalte() {
		return new RestTemplate();
	}


	public static void main(String[] args) {
		SpringApplication.run(NbaPlayerApiApplication.class, args);
	}

}
