package chapter6.util;

import java.text.DateFormat;
import java.util.*;

public class LocaleTest {
	public static void main(String[] args) {
		Date date = new Date();
		Locale loc = new Locale("it","IT");
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, loc);
		System.out.println("Italy Locale : " + df.format(date));
		loc = new Locale("hi","IN");
		df = DateFormat.getDateInstance(DateFormat.FULL,loc);
		System.out.println("Indian Locale: " + df.format(date));
	}
}
