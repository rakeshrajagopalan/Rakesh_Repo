package chapter6.regex;

import java.io.*;
import java.util.regex.*;

public class FindNReplace {
	public static void main(String[] args) {
		try {
			String findText = "[aA]nnotation";
			String replaceText = "Rakesh";
			File file = new File("f:\\Annotation_Tutorial.txt");
			int replacementNumber = 0;
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuffer builder = new StringBuffer();
			String nextLine;
			while ((nextLine = reader.readLine()) != null) {
				builder.append(nextLine);
				builder.append("\n");
			}
			System.out.println("The original text is: " + builder.toString());
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");
			String text = builder.toString();
			Pattern pattern = Pattern.compile(findText);
			Matcher matcher = pattern.matcher(text);
			boolean result = matcher.find();
			builder = new StringBuffer();
			while (result) {
				matcher.appendReplacement(builder, replaceText);
				result = matcher.find();
				replacementNumber += 1;
			}
			matcher.appendTail(builder);
			System.out.println("The replaced text is: " + builder.toString());
			System.out.println(replacementNumber + " occurance(s) replaced");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
