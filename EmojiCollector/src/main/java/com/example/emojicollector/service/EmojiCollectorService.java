package com.example.emojicollector.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.emojicollector.dto.EmojiDto;
import com.example.emojicollector.dto.EmojiDtoToClient;
import com.example.emojicollector.errors.EmojiNotFoundException;
import com.example.emojicollector.errors.ErrorMessages;
import com.example.emojicollector.errors.FieldInvalidException;
import com.example.emojicollector.feign.EmojiCollectorFeign;
import com.example.emojicollector.model.Emoji;
import com.example.emojicollector.repository.EmojiCollectorRepository;

@Service
public class EmojiCollectorService {

	EmojiCollectorRepository emojiCollectorRepository;
	EmojiCollectorFeign emojiCollectorFeign;

	EmojiCollectorService(EmojiCollectorRepository emojiCollectorRepository, EmojiCollectorFeign emojiCollectorFeign){
		this.emojiCollectorRepository = emojiCollectorRepository;
		this.emojiCollectorFeign = emojiCollectorFeign;
	}

	public List<Emoji> getEmojiFromProvider() {
		return emojiCollectorFeign.getEmojiFromProvider().stream().map(EmojiDto::convertDtoToEmoji).collect(Collectors.toList());
	}

	public long saveAllEmojiToDB()  {
		List<Emoji> emojiList = getEmojiFromProvider();

		emojiCollectorRepository.saveAll(emojiList);
		return emojiList.size();
	}

	public List<EmojiDtoToClient> savePageEmojiToDB(int page, int size) {
		List<Emoji> emojiList = getEmojiFromProvider();

		if(page * size > emojiList.size()) {
			throw new FieldInvalidException(ErrorMessages.PAGE_AND_SIZE);
		}

		int start =  size * page;
		int end = Math.min( size * (page + 1) , emojiList.size());

		List<Emoji> emojiPageList = IntStream.range(start,end)
				.mapToObj(emojiList::get)
				.collect(Collectors.toList());

		return EmojiDtoToClient.convertListEmojiToClient(emojiCollectorRepository.saveAll(emojiPageList));

	}

	public List<EmojiDtoToClient> getAllEmoji() {
		return EmojiDtoToClient.convertListEmojiToClient(emojiCollectorRepository.findAll());
	}

	public Emoji getEmojiId(long id) {
		return emojiCollectorRepository.findById(id).
				orElseThrow(() -> new EmojiNotFoundException(ErrorMessages.NOT_FOUND));
	}

	public EmojiDtoToClient getEmojiDtoClientById(long id) {
		return EmojiDtoToClient.convertEmojiToClient(getEmojiId(id));
	}

	public List<EmojiDtoToClient> getEmojiByName(String name) {
		return EmojiDtoToClient.convertListEmojiToClient(emojiCollectorRepository.findAllByName(name));
	}

	public List<EmojiDtoToClient> getEmojiByCategory(String category) {
		return EmojiDtoToClient.convertListEmojiToClient(emojiCollectorRepository.findAllByCategory(category));
	}

	public List<EmojiDtoToClient> getEmojiByGroup(String group) {
		return EmojiDtoToClient.convertListEmojiToClient(emojiCollectorRepository.findAllByGroup(group));
	}

	public List<EmojiDtoToClient> getEmojiByCategoryAndGroup(String category, String group) {
		return EmojiDtoToClient.convertListEmojiToClient(emojiCollectorRepository.findAllByCategoryAndGroup(category, group));
	}

	public List<EmojiDtoToClient> getEmojiByHtmlCodeCustom(String htmlCode) {
		return EmojiDtoToClient.convertListEmojiToClient(emojiCollectorRepository.findAllByHtmlCodeCustom(htmlCode));
	}

	public List<EmojiDtoToClient> getEmojiByUnicodeCustom(String unicode) {
		unicode = unicode.replace(" ", "+");
		return EmojiDtoToClient.convertListEmojiToClient(emojiCollectorRepository.findAllByUnicodeCustom(unicode));
	}

	public EmojiDtoToClient addEmoji(EmojiDto emojiDto) {
		Emoji emoji = EmojiDto.convertDtoToEmoji(emojiDto);
		return EmojiDtoToClient.convertEmojiToClient(emojiCollectorRepository.save(emoji));
	}

	public EmojiDtoToClient addHtmlCodeToEmoji(String htmlCode, long id) {
		Emoji emoji = getEmojiId(id);

		if(!emoji.getHtmlCode().contains(htmlCode)) {
			emoji.getHtmlCode().add(htmlCode);
		}

		return EmojiDtoToClient.convertEmojiToClient(emojiCollectorRepository.save(emoji));
	}

	public EmojiDtoToClient addUnicodeToEmoji(String unicode, long id) {
		Emoji emoji = getEmojiId(id);
		unicode = unicode.replace(" ", "+");

		if(!emoji.getUnicode().contains(unicode)) {
			emoji.getUnicode().add(unicode);
		}

		return EmojiDtoToClient.convertEmojiToClient(emojiCollectorRepository.save(emoji));
	}

	public void deleteEmoji(long id) {
		Emoji emoji = getEmojiId(id);
		emojiCollectorRepository.delete(emoji);
	}

	public void deleteHtmlCodeToEmoji(String htmlCode, long id) {
		Emoji emoji = getEmojiId(id);

		if(!emoji.getHtmlCode().remove(htmlCode)) {
			throw new EmojiNotFoundException(ErrorMessages.HTML_CODE_NOT_FOUND);
		}

		emojiCollectorRepository.save(emoji);
	}

	public void deleteUnicodeToEmoji(String unicode, long id) {
		Emoji emoji = getEmojiId(id);
		unicode = unicode.replace(" ", "+");

		if(!emoji.getUnicode().remove(unicode)) {
			throw new EmojiNotFoundException(ErrorMessages.UNICODE_NOT_FOUND);
		}

		emojiCollectorRepository.save(emoji);
	}
	public EmojiDtoToClient updateEmoji(EmojiDto emojiDto, long id) {
		Emoji emoji = getEmojiId(id).toBuilder().
						name(emojiDto.getName()).
						category(emojiDto.getCategory()).
						group(emojiDto.getGroup()).
						htmlCode(new ArrayList<>(emojiDto.getHtmlCode())).
						unicode(new ArrayList<>(emojiDto.getUnicode())).
						build();

		return EmojiDtoToClient.convertEmojiToClient(emojiCollectorRepository.save(emoji));
	}
}

