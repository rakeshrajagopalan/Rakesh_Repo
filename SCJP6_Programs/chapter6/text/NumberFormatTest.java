package chapter6.text;

import java.text.NumberFormat;
import java.text.ParseException;

public class NumberFormatTest {
	public static void main(String[] args) throws ParseException {
		float f = 3234.45345f;
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		System.out.println(nf.format(f));
		nf.setMaximumFractionDigits(4);
		System.out.println(nf.format(f));
		nf = NumberFormat.getInstance();
		nf.setParseIntegerOnly(true);
		System.out.println(nf.parse(String.valueOf(f)));
	}
}
