package com.java8.chap01;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NIOFileDemo {
	public static void main(String[] args) throws Exception {
		Path source = Paths.get("C:\\Users\\Rakesh\\Desktop\\MetroSmartCardSystem\\");
		Path target = Paths.get("d:\\temp\\test2.txt");
		Files.copy(source, target);
	}
}
