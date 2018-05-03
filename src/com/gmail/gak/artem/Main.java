package com.gmail.gak.artem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Dictionary d = new Dictionary();
		d.loadFromFile("./dictionary.data");

		addWordTranslation(d);

		d.saveToFile("./dictionary.data");
		d.loadFromFile("./dictionary.data");

		Translator tr = new Translator(d);

		String str = translateTextFromFile("./English.in", tr);
		writeTranslateTextToFile("./Ukrainian.out", str);
	}

	public static void addWordTranslation(Dictionary d) {
		String word = "";
		String value = "";
		Scanner sc = new Scanner(System.in);

		for (; true;) {
			System.out.println("Enter \":q\" to quit");
			System.out.println("Set Word:");
			word = sc.nextLine().toLowerCase();
			if (word.equals(":q")) {
				break;
			}
			System.out.println("Set Translation of " + word + ":");
			value = sc.nextLine().toLowerCase();
			if (value.equals(":q")) {
				break;
			}
			d.setWordTranslate(word, value);
		}

		sc.close();
	}

	public static String translateTextFromFile(String path, Translator tr) {
		String result = "";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			StringBuilder sb = new StringBuilder();
			for (; (line = br.readLine()) != null;) {
				sb.append(tr.translate(line));
			}
			result = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void writeTranslateTextToFile(String path, String str) {
		try (FileWriter writer = new FileWriter(path, false)) {
			writer.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
