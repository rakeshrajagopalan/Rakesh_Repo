package com.metroscs.farestrategy;

public class FareStrategyFactory {

	public static FareStrategy getFareStrategy(int dayOfWeek) {
		FareStrategy fareStrategy = null;
		switch (dayOfWeek) {
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			fareStrategy = new WeekdayFareStrategy();
			break;
		case 1:
		case 7:
			fareStrategy = new WeekendFareStrategy();
			break;
		}
		return fareStrategy;
	}

}
