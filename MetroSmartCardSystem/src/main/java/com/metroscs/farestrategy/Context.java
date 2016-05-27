package com.metroscs.farestrategy;

public class Context {
	
	private FareStrategy fareStrategy;
	
	public Context(FareStrategy fareStrategy) {
		this.fareStrategy = fareStrategy;
	}
	
	public float executeStrategy() {
		return fareStrategy.getFareCharge();
	}

}
