package chapter6.util;

import java.util.Calendar;

public class CalendarTest {
	public static void main(String[] args) {
		CalendarTest ct = new CalendarTest();
		ct.method1();
		ct.rollMethodDemo();
	}

	private void method1() {
		Calendar calendar = Calendar.getInstance();
		System.out.println("Current Date: " + calendar.getTime());
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.MONTH, 5);
		System.out.println("After 5 months: " + calendar1.getTime());
		System.out.println(calendar1.after(calendar));
		System.out.println(calendar1.before(calendar));
		Calendar c3 = Calendar.getInstance();
		c3.add(Calendar.DATE, 5);
		System.out.println(c3.getTime());
	}

	private void rollMethodDemo() {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.MONTH, 5);
		System.out.println("After 5 months: " + calendar1.getTime());
		Calendar calendar2 = Calendar.getInstance();
		calendar1.roll(Calendar.MONTH, 5);
		System.out.println("After 5 months: " + calendar2.getTime()); 
		//The year doesnt change in the above line!
		Calendar x3 = Calendar.getInstance();
		x3.set(2008, 10, 05);
		System.out.println(x3.getTime());
	}

}
