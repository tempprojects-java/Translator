package com.gmail.gak.artem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Translator {

	private Dictionary dictionary;

	public Translator(Dictionary dictionary) {
		super();
		this.dictionary = dictionary;
	}

	public Translator() {
		super();
		this.dictionary = new Dictionary();
	}

	public String translate(String str) {
		if(str == null) {
			throw new IllegalArgumentException();
		}
		
		str = prepareString(str);

		StringBuilder sb = new StringBuilder();
		List<String> text = new ArrayList<>(Arrays.asList(str.split("\\s")));

		for (String word : text) {
			String sWord = prepareWord(word);
			String value = dictionary.getWordTranslate(sWord);
			if (value != null) {
				if (word.matches("^[A-Z].*$")) {
					value = value.substring(0, 1).toUpperCase() + value.substring(1);
				}
				sb.append(word.replaceAll(sWord, value) + " ");
			} else {
				sb.append(word + " ");
			}
		}
		return sb.toString();
	}

	private String prepareWord(String str) {
		return str.replaceAll("\\.|,|\\?|\\!", "");
	}

	private String prepareString(String str) {
		return str.replaceAll("(=?\\s)(of|a|or|to|the)(?=\\s)", "");
	}

	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

}
