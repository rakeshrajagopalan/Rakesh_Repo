package chapter6.io;

import java.io.File;
import java.io.IOException;

public class DirTest {
	public static void main(String[] args) throws IOException {
		File file = new File("d:\\Three");
		file.mkdir();
		File myFile = new File(file, "1.txt");
		myFile.createNewFile();
		File test = new File("d:\\Three");
		File[] files = test.listFiles();
		for (File f : files) {
			System.out.println(f.getName());
		}
	}
}
