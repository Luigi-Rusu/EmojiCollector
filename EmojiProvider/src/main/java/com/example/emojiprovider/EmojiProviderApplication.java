package com.example.emojiprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EmojiProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmojiProviderApplication.class, args);
	}

}
