package com.example.emojicollector.dto;


import java.util.List;
import java.util.stream.Collectors;

import com.example.emojicollector.model.Emoji;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmojiDtoToClient {
	private String name;
	private String category;
	private String group;
	public static EmojiDtoToClient convertEmojiToClient(Emoji emoji) {
		return EmojiDtoToClient.builder().
				name(emoji.getName()).
				category(emoji.getCategory()).
				group(emoji.getGroup()).
				build();
	}
	public static List<EmojiDtoToClient> convertListEmojiToClient(List<Emoji> emojiList) {
		return emojiList.stream().
				map(EmojiDtoToClient::convertEmojiToClient).
				collect(Collectors.toList());
	}
}