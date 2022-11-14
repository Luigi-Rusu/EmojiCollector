package com.example.emojicollector.errors;


public class EmojiNotFoundException extends RuntimeException{
	public EmojiNotFoundException(String message) {
		super(message);
	}
}
