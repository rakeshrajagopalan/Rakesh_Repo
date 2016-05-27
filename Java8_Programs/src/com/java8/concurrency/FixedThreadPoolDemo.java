package com.java8.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {
	public static void main(String[] args) {
		final int cpus = Runtime.getRuntime().availableProcessors();
		ExecutorService executorService = Executors.newFixedThreadPool(cpus);
		executorService.submit(new MyTask());
		executorService.shutdown();
	}
}