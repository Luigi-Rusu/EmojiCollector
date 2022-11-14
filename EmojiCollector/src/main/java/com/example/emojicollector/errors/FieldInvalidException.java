package com.example.emojicollector.errors;

public class FieldInvalidException extends RuntimeException{
	public FieldInvalidException(String message) {
		super(message);
	}
}
