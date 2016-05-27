package chapter6.util;

import java.util.Calendar;
import java.util.Date;

public class DateTest {
	public static void main(String[] args) {
		Date date = new Date(1000000000000L);
		System.out.println(date.toString());
		date.setTime(3000);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE,1);
		c.add(Calendar.MONTH,5);
		c.add(Calendar.YEAR,3);
		System.out.println(c.getTime());
	}
}
