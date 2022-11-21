package com.example.emojicollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EmojiCollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmojiCollectorApplication.class, args);
	}

}
