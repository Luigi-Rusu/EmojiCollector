package com.example.emojicollector.controller;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.emojicollector.dto.EmojiDto;
import com.example.emojicollector.dto.EmojiDtoToClient;
import com.example.emojicollector.errors.ErrorMessages;
import com.example.emojicollector.service.EmojiCollectorService;

@RestController
@RequestMapping("/api/collector")
@Validated
public class EmojiCollectorController {

	EmojiCollectorService emojiCollectorService;

	EmojiCollectorController(EmojiCollectorService emojiCollectorService) {
		this.emojiCollectorService = emojiCollectorService;
	}

	@GetMapping("/save")
	public ResponseEntity<Long> saveEmojiToDB() {
		return new ResponseEntity<>(emojiCollectorService.saveAllEmojiToDB(), HttpStatus.OK);
	}

	@GetMapping("/save/page")
	public ResponseEntity<List<EmojiDtoToClient>> savePageEmojiToDB(@RequestParam(name = "page")
	                                                                @Min(value = 0 , message = ErrorMessages.PAGE)
	                                                                int page,

	                                                                @RequestParam(name = "size")
	                                                                @Min(value = 1, message = ErrorMessages.SIZE)
	                                                                int size) {
		return new ResponseEntity<>(emojiCollectorService.savePageEmojiToDB(page,size), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<EmojiDtoToClient>> getEmoji(){
		return new ResponseEntity<>(emojiCollectorService.getAllEmoji(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmojiDtoToClient> getEmojiById(@PathVariable
	                                                     @Min(value = 1, message = ErrorMessages.ID)
	                                                     long id) {
		return new ResponseEntity<>(emojiCollectorService.getEmojiDtoClientById(id), HttpStatus.OK);
	}

	@GetMapping("/name")
	public ResponseEntity<List<EmojiDtoToClient>> getEmojiByName(@RequestParam(name = "name")
	                                                             @NotBlank(message = ErrorMessages.NAME)
	                                                             String name) {
		return new ResponseEntity<>(emojiCollectorService.getEmojiByName(name), HttpStatus.OK);
	}

	@GetMapping("/category")
	public ResponseEntity<List<EmojiDtoToClient>> getEmojiByCategory(@RequestParam(name = "category")
	                                                                 @NotBlank(message = ErrorMessages.CATEGORY)
	                                                                 String category) {
		return new ResponseEntity<>(emojiCollectorService.getEmojiByCategory(category), HttpStatus.OK);
	}

	@GetMapping("/group")
	public ResponseEntity<List<EmojiDtoToClient>> getEmojiByGroup(@RequestParam(name = "group")
	                                                              @NotBlank(message = ErrorMessages.GROUP)
	                                                              String group) {
		return new ResponseEntity<>(emojiCollectorService.getEmojiByGroup(group), HttpStatus.OK);
	}

	@GetMapping("/category&group")
	public ResponseEntity<List<EmojiDtoToClient>> getEmojiByCategoryAndGroup(@RequestParam(name = "category")
	                                                                         @NotBlank(message = ErrorMessages.CATEGORY)
	                                                                         String category,

	                                                                         @RequestParam(name = "group")
	                                                                         @NotBlank(message = ErrorMessages.GROUP)
	                                                                         String group) {
		return new ResponseEntity<>(emojiCollectorService.getEmojiByCategoryAndGroup(category, group), HttpStatus.OK);
	}

	@GetMapping("/htmlcode")
	public ResponseEntity<List<EmojiDtoToClient>> getEmojiByHtmlCodeCustom(@RequestParam(name = "htmlCode")
	                                                                       @NotBlank(message = ErrorMessages.HTMLCODE)
	                                                                       String htmlCode) {
		return new ResponseEntity<>(emojiCollectorService.getEmojiByHtmlCodeCustom(htmlCode), HttpStatus.OK);
	}

	@GetMapping("/unicode")
	public ResponseEntity<List<EmojiDtoToClient>> getEmojiByUnicodeCustom(@RequestParam(name = "unicode")
	                                                                      @NotBlank(message = ErrorMessages.UNICODE)
	                                                                      String unicode) {
		return new ResponseEntity<>(emojiCollectorService.getEmojiByUnicodeCustom(unicode), HttpStatus.OK);
	}


	@PostMapping
	public ResponseEntity<EmojiDtoToClient> addEmoji(@RequestBody @Validated EmojiDto emojiDto) {
		return new ResponseEntity<>(emojiCollectorService.addEmoji(emojiDto), HttpStatus.OK);
	}

	@PostMapping("/htmlcode/{id}")
	public ResponseEntity<EmojiDtoToClient> addHtmlCode(@RequestParam(name = "htmlCode")
	                                                    @NotBlank(message = ErrorMessages.HTMLCODE)
	                                                    String htmlCode,

	                                                    @PathVariable
	                                                    @Min(value = 1, message = ErrorMessages.ID)
	                                                    long id) {
		return new ResponseEntity<>(emojiCollectorService.addHtmlCodeToEmoji(htmlCode, id), HttpStatus.OK);
	}

	@PostMapping("/unicode/{id}")
	public ResponseEntity<EmojiDtoToClient> addUnicode(@RequestParam(name = "unicode")
	                                                   @NotBlank(message = ErrorMessages.UNICODE)
	                                                   String unicode,

	                                                   @PathVariable
	                                                   @Min(value = 1, message = ErrorMessages.ID)
	                                                   long id) {
		return new ResponseEntity<>(emojiCollectorService.addUnicodeToEmoji(unicode, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void deleteEmoji(@PathVariable @Min(value = 1,message = ErrorMessages.ID) long id) {
		emojiCollectorService.deleteEmoji(id);
	}

	@DeleteMapping("/htmlcode/{id}")
	public void deleteHtmlCode(@RequestParam(name = "htmlCode")
	                           @NotBlank(message = ErrorMessages.HTMLCODE)
	                           String htmlCode,

	                           @PathVariable
	                           @Min(value = 1, message = ErrorMessages.ID)
	                           long id) {
		emojiCollectorService.deleteHtmlCodeToEmoji(htmlCode, id);
	}

	@DeleteMapping("/unicode/{id}")
	public void deleteUnicode(@RequestParam(name = "unicode")
	                          @NotBlank(message = ErrorMessages.UNICODE)
	                          String unicode,

	                          @PathVariable
	                          @Min(value = 1, message = ErrorMessages.ID)
	                          long id) {
		emojiCollectorService.deleteUnicodeToEmoji(unicode, id);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<EmojiDtoToClient> updateEmoji(@PathVariable
	                                                    @Min(value = 1, message = ErrorMessages.ID)
	                                                    long id,

	                                                    @RequestBody
	                                                    @Validated
	                                                    EmojiDto emojiDto) {
		return new ResponseEntity<>(emojiCollectorService.updateEmoji(emojiDto, id), HttpStatus.OK);
	}
}
