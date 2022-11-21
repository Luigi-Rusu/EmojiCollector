package com.example.emojiprovider.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.emojiprovider.dto.EmojiDto;
import com.example.emojiprovider.feign.EmojiProviderFeign;

@RestController
@RequestMapping("/api/provider")
public class EmojiProviderController {

	EmojiProviderFeign emojiProviderFeign;

	EmojiProviderController(EmojiProviderFeign emojiProviderFeign) {
		this.emojiProviderFeign = emojiProviderFeign;
	}

	@GetMapping
	public List<EmojiDto> getEmojiFromApi(){
		return emojiProviderFeign.getEmojiFromApi();
	}

}
