package chapter6.io;

import java.io.*;

public class WriteFile {
	public static void main(String[] args) {
		try {
			String[] array = { "Jai Sri Ram", "The Lord is my Shepherd",
					"Praise the Lord" };
			File file = new File("D:\\1.txt");
			FileWriter writer = new FileWriter(file);
			for (String s : array) {
				writer.write(s);
			}
			writer.flush();
			writer.close();
			char[] chars = new char[(int) file.length()];
			FileReader reader = new FileReader(file);
			reader.read(chars);
			reader.close();
			System.out.println("The file contents are: " + new String(chars));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
