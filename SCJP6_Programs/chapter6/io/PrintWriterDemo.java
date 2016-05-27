package chapter6.io;

import java.io.*;

public class PrintWriterDemo {
	public static void main(String[] args) {
		try {
			String[] array = { "Jai Sri Ram", "The Lord is my Shepherd",
					"Praise the Lord" };
			File file = new File("d:/2.txt");
			FileWriter fWriter = new FileWriter(file);
			PrintWriter writer = new PrintWriter(fWriter);
			for (String s : array) {
				writer.println(s);
			}
			writer.flush();
			writer.close();
			BufferedReader reader = new BufferedReader(new FileReader(file));
			System.out.println("----------------------------------");
			System.out.println("The file contents: ");
			String data;
			while ((data = reader.readLine()) != null) {
				System.out.println(data);
			}
			System.out.println("----------------------------------");
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
