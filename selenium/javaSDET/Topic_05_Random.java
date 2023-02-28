package javaSDET;

import java.util.Random;

public class Topic_05_Random {
	public static void main(String[] args) {
		Random rand = new Random();
		rand.nextInt();

		String emailAddress = "automation" + rand.nextInt(999) + "@gmail.vn";
		System.out.println(emailAddress);
	}
}
