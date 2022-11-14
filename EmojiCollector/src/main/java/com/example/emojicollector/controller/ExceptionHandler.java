package com.example.emojicollector.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.example.emojicollector.errors.EmojiNotFoundException;
import com.example.emojicollector.errors.FieldInvalidException;

@ControllerAdvice
public class ExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler(value = {FieldInvalidException.class})
	public ResponseEntity<String> handleBadRequests(RuntimeException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(value = {EmojiNotFoundException.class})
	public ResponseEntity<String> handleNoDataFoundException(RuntimeException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
}
