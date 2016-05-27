package testpack;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Test{
	public static void main(String[] args) throws Exception {
		Date date = new SimpleDateFormat("yyyy-mm-dd").parse("2012-01-15");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		System.out.println(c.get(Calendar.DAY_OF_MONTH));
		System.out.println(c.get(Calendar.MONTH));
	}
}
