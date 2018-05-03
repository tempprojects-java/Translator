package com.gmail.gak.artem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Dictionary {
	private Map<String, String> data;

	public Dictionary() {
		super();
		data = new HashMap<>();
	}

	public void loadFromFile(String path) {
		if (path == null) {
			throw new IllegalArgumentException();
		}

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;

			for (; (line = br.readLine()) != null;) {
				String[] buffer = line.split(":\\s");
				data.put(buffer[0], buffer[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getWordTranslate(String word) {
		if (data.size() == 0 || word == null) {
			return null;
		}

		Set<String> keys = data.keySet();
		for (String key : keys) {
			if (key.equals(word.toLowerCase())) {
				return data.get(key);
			}
		}

		return null;
	}

	public void setWordTranslate(String word, String value) {
		if (word == null || value == null) {
			throw new IllegalArgumentException();
		}

		data.put(word, value);
	}

	public void clear() {
		data.clear();
	}

	public void saveToFile(String path) {
		if (path == null) {
			throw new IllegalArgumentException();
		}

		try (FileWriter f = new FileWriter(path, false)) {

			Set<String> keys = data.keySet();
			for (String key : keys) {
				f.write(key + ": " + data.get(key) + "\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

}
