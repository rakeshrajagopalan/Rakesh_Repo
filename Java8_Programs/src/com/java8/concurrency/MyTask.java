package com.java8.concurrency;

import java.util.concurrent.Callable;

class MyTask implements Callable<String> {
	@Override
	public String call() throws Exception {
		double s = 0.0d;
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000000; i++) {
			s = (Math.random() * 10)*10;
		}
		long end = System.currentTimeMillis();
		System.out.println("Time taken: " + (end - start));
		System.out.println(s);
		return String.valueOf(s);
	}
}
