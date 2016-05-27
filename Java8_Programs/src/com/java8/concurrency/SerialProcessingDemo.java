package com.java8.concurrency;

public class SerialProcessingDemo {
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		new MyTask().call();
		long end = System.currentTimeMillis();
		System.out.println("Time taken: " + (end - start));
		//SIngle thread takes around 30 seconds to process 1 billion loops
	}
}
