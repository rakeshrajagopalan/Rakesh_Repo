package chapter6.text;

import java.text.*;
import java.util.*;

public class DateFormatTest {
	public static void main(String[] args) throws ParseException {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(d));
		DateFormat[] dfa = new DateFormat[6];
		dfa[0] = DateFormat.getInstance();
		dfa[1] = DateFormat.getDateInstance();
		dfa[2] = DateFormat.getDateInstance(DateFormat.SHORT);
		dfa[3] = DateFormat.getDateInstance(DateFormat.MEDIUM);
		dfa[4] = DateFormat.getDateInstance(DateFormat.LONG);
		dfa[5] = DateFormat.getDateInstance(DateFormat.FULL);
		for (DateFormat df : dfa) {
			System.out.println(df.format(d));
		}
		DateFormat dfor = dfa[0];
		String s = dfor.format(d);
		System.out.println(dfor.parse(s));
		String ss = "2008-08-01";
		System.out.println(dfa[0].parse(ss));
	}
}
