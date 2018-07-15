package com.babycard.util;

public class RandomUtil {

	public static String dmAr[] = new String[] { "8", "E", "Z", "2", "3", "J", "K", "L", "M", "N", "P", "Q", "G", "H",
			"T", "U", "V", "W", "X", "Y", "A", "B", "9", "R", "C", "D", "5", "6", "7", "S", "4", "F" };

	public static int DAIMA_LENGTH = 4;

	public String buildCode() {
		String daima = "";
		for (int i = 0; i < DAIMA_LENGTH; i++) {
			daima += dmAr[(int) (Math.random() * dmAr.length)];
		}
		return daima;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String daima = "";
		for (int i = 0; i < DAIMA_LENGTH; i++) {
			daima += dmAr[(int) (Math.random() * dmAr.length)];
		}
		System.out.println(daima);

	}

}
