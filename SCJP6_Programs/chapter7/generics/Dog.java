package chapter7.generics;

public class Dog extends Animal {
	public void eat(Animal animal) {
		System.out.println("Im eating like a Dog");
		((Dog) animal).shout();
	}
	private void shout() {
		System.out.println("Woof-woof");
	}
}
