package dsapplications;

import java.io.File;
import java.util.*;

public class Recursor {

	private List<File> fileNameList = new ArrayList<File>();

	private List<File> dirNameList = new ArrayList<File>();

	public void listFilesInDir(String path) {
		File file = new File(path);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File nextFile : files) {
				if (nextFile.isDirectory()) {
					dirNameList.add(nextFile);
				} else {
					fileNameList.add(nextFile);
				}
			}
			if (dirNameList.size() > 0) {
				File nextFile = dirNameList.get(0);
				dirNameList.remove(0);
				listFilesInDir(nextFile.getPath());
			}
		} else {
			fileNameList.add(file);
		}
	}

	public static void main(String[] args) {
		Recursor recursor = new Recursor();
		recursor.listFilesInDir("E:\\Devotional Songs\\");
		for(File f : recursor.fileNameList) {
			System.out.println(f.getName());
		}
	}
}
