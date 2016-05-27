package com.java8.chap01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileNamePrinter {
	public static void main(String[] args) throws IOException {
		String regex = "\\w+.html";
		String dirPath = "D:\\eclipse";
		List<Path> paths = Files.walk(Paths.get(dirPath))
				.filter(Files::isRegularFile).collect(Collectors.toList());
		paths.parallelStream()
				.filter(path -> path.getFileName().toString().matches(regex))
				.collect(Collectors.toList()).forEach(System.out::println);
	}
}
