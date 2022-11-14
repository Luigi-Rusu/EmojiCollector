package com.example.emojiprovider.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.emojiprovider.dto.EmojiDto;

@Service
public class EmojiProviderService {
	public List<EmojiDto> getEmojiFromApi() {
		CompletableFuture<List<EmojiDto>> cf =  WebClient.create("https://emojihub.herokuapp.com/api/all")
				.get()
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntityList(EmojiDto.class)
				.mapNotNull(HttpEntity::getBody)
				.toFuture();

		try {
			return cf.get();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}
}
