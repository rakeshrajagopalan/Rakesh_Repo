package chapter2;

class Vehicle {
	public void name() {
		System.out.println("Vehicle");
	}
}

class Car extends Vehicle {
	public void name() {
		System.out.println("Car");
	}
	public void category() {
		System.out.println("LMV");
	}
}

public class ClassCastExample {
	public static void main(String[] args) {
		Vehicle v = new Car();
		Car c = (Car) v;
		c.category(); //LMV
		Vehicle veh = new Vehicle();
		Car car = (Car) veh; // ClassCastException
		car.category();
	}
}
