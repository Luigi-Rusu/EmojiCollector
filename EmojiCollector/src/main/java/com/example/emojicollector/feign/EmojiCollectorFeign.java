package com.example.emojicollector.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.emojicollector.dto.EmojiDto;

@FeignClient(value = "feignCollector" , url = "http://localhost:9090/api/provider")
public interface EmojiCollectorFeign {
	@GetMapping
	List<EmojiDto> getEmojiFromProvider();
}
