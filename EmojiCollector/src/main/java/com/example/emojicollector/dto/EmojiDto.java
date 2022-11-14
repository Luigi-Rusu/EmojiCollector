package com.example.emojicollector.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.emojicollector.errors.ErrorMessages;
import com.example.emojicollector.model.Emoji;

import lombok.Data;

@Data
public class EmojiDto {
	@NotBlank(message = ErrorMessages.NAME)
	private String name;
	@NotNull(message = ErrorMessages.CATEGORY)
	private String category;
	@NotBlank(message = ErrorMessages.GROUP)
	private String group;
	@NotNull(message = ErrorMessages.HTMLCODE)
	private List<@NotBlank(message = ErrorMessages.HTMLCODE) String> htmlCode;
	@NotNull(message = ErrorMessages.UNICODE)
	private List<@NotBlank(message = ErrorMessages.UNICODE) String> unicode;
	public static Emoji convertDtoToEmoji(EmojiDto emojiDto) {
		return Emoji.builder()
				.name(emojiDto.name)
				.category(emojiDto.category)
				.group(emojiDto.group)
				.htmlCode(emojiDto.htmlCode)
				.unicode(emojiDto.unicode)
				.build();
	}
}
