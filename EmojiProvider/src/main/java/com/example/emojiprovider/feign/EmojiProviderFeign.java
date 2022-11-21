package com.example.emojiprovider.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.emojiprovider.dto.EmojiDto;


@FeignClient(value = "feignProvider", url = "https://emojihub.herokuapp.com/api/all")
public interface EmojiProviderFeign {
	@GetMapping
	List<EmojiDto> getEmojiFromApi();
}
