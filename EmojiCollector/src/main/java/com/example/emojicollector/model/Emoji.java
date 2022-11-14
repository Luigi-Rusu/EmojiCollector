package com.example.emojicollector.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "emoji")
@Table(name = "emoji")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Emoji {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "emoji_name")
	private String name;

	@Column(name = "emoji_category")
	private String category;

	@Column(name = "emoji_group")
	private String group;

	@ElementCollection
	@CollectionTable(name = "emoji_html_code",
			joinColumns = @JoinColumn( name = "emoji_id"))
	@Builder.Default
	private List<String> htmlCode = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "emoji_unicode",
			joinColumns = @JoinColumn( name = "unicode_id"))
	@Builder.Default
	private List<String> unicode = new ArrayList<>();

}
