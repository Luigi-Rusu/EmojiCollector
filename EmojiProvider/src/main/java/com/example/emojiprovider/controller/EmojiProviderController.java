package com.example.emojiprovider.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.emojiprovider.dto.EmojiDto;
import com.example.emojiprovider.service.EmojiProviderService;

@RestController
@RequestMapping("/api/provider")
public class EmojiProviderController {

	EmojiProviderService emojiProviderService;

	EmojiProviderController(EmojiProviderService emojiProviderService) {
		this.emojiProviderService = emojiProviderService;
	}

	@GetMapping
	public List<EmojiDto> getEmojiFromApi(){
		return emojiProviderService.getEmojiFromApi();
	}

}
