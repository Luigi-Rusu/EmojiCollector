package com.example.emojiprovider.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.emojiprovider.errors.ErrorMessages;

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
}
