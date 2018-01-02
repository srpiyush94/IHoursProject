package com.test;

import java.util.Scanner;

public class WordDemo {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter some sentence");
		String sentence = s.nextLine();
		System.out.println("Enter word to to follow ");
		char word = s.next().charAt(0);
		int counter =0;
		char[] chars = sentence.toCharArray();

		for (int i = 0; i < chars.length; i++) {
		System.out.print("| " + i + " |");
		System.out.println(" " + chars[i] + " |");
		System.out.println("-------------");
		}

		for (int i = 0; i < chars.length; i++) {

		if(sentence.charAt(i) == word){
		counter++;
		}
		}
		System.out.println("Word " + word + " appears in sentence " + counter + " times");

	

	}

}
