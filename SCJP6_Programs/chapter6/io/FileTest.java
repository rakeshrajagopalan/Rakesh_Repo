package chapter6.io;

import java.io.*;

public class FileTest {
	public static void main(String[] args) {
		try {
			File file = new File("d://1.txt");
			file.createNewFile();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
