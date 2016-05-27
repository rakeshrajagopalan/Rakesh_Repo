package trickyquestions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileRenamer {
	
	private static final String PATH = "D:\\1\\";
	
	private String textToFind = "ABCD";
	
	private String textToReplace = "Rakesh";
	
	private List<String> filenameList = new ArrayList<String>();
	
	public FileRenamer() {
		try {
			File file = new File(PATH);
			File[] files = file.listFiles();
			for(File nextFile : files) {
				populateFileNames(nextFile);
			}
			for(String nextName : filenameList) {
				renameFile(new File(nextName));
			}
			System.out.println(filenameList.size() + " renamed");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void populateFileNames(File file) throws Exception {
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File nextFile : files) {
				populateFileNames(nextFile);
			}
		} else {
			filenameList.add(file.getAbsolutePath());
		}
	}
	
	private void renameFile(File file) throws Exception {
		if(!file.isDirectory()) {
			String targetName = file.getAbsolutePath().replaceAll(textToFind, textToReplace);
			file.renameTo(new File(targetName));
		}
	}
	
	public static void main(String[] args) {
		new FileRenamer();
	}

}
