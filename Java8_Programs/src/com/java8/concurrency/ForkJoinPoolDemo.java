package com.java8.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class ForkJoinPoolDemo {
	public static void main(String[] args) throws Exception {
		ForkJoinPool fork = new ForkJoinPool();
		Future<String> f = fork.submit(new MyTask());
		System.out.println(f.get());
	}
}
