package com.java8.chap01;

public class ThreadLambda {
	
	public void lambdaThread() {
		Runnable runnable = () -> {
			String threadName = Thread.currentThread().getName();
			System.out.println("Hello " + threadName);
		};
		runnable.run();
		Thread thread = new Thread(runnable);
		thread.start();
		System.out.println("Done");
	}
	
	public static void main(String[] args) {
		new ThreadLambda().lambdaThread();
	}
}
