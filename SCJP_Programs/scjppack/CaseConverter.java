package scjppack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CaseConverter {
	
	public static void main(String[] args) throws Exception {
		String filePath = "E:\\File.txt";
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
		String data;
		while((data = reader.readLine()) != null) {
			builder.append(data.toUpperCase());
			builder.append("\n");
		}
		reader.close();
		PrintWriter writer = new PrintWriter(new FileWriter(new File(filePath)));
		writer.println(builder.toString().trim());
		writer.close();
	}

}
