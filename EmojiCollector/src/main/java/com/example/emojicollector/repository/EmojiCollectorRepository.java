package com.example.emojicollector.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.emojicollector.model.Emoji;

public interface EmojiCollectorRepository extends JpaRepository<Emoji, Long> {
	List<Emoji> findAllByName(@Param("name") String name);
	List<Emoji> findAllByCategory(@Param("category") String category);
	List<Emoji> findAllByGroup(@Param("group") String group);
	List<Emoji> findAllByCategoryAndGroup(@Param("category") String category,
	                                      @Param("group") String group);
	@Query(value = "SELECT e FROM  emoji as e WHERE :htmlCode IN elements(e.htmlCode)")
	List<Emoji> findAllByHtmlCodeCustom(String htmlCode);
	@Query(value = "SELECT e FROM  emoji as e WHERE :unicode IN elements(e.unicode)")
	List<Emoji> findAllByUnicodeCustom(String unicode);

}
