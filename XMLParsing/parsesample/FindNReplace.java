package parsesample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindNReplace {
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"D:\\Input.xml"));
			StringBuffer fileContents = new StringBuffer();
			String temp;
			while ((temp = reader.readLine()) != null) {
				fileContents.append(temp);
				fileContents.append("\n");
			}
			reader.close();
			Pattern pattern = Pattern.compile("</.+");
			Matcher matcher = pattern.matcher(fileContents.toString());
			boolean result = matcher.find();
			fileContents = new StringBuffer();
			while (result) {
				matcher.appendReplacement(fileContents, " ");
				result = matcher.find();
			}
			matcher.appendTail(fileContents);
			PrintWriter writer = new PrintWriter(new FileWriter(
					"D:\\Output.xml"));
			writer.write(fileContents.toString());
			writer.flush();
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
