package chapter7.generics;

public class Cat extends Animal {
	public void eat(Animal animal) {
		System.out.println("Im eating like a Cat");
		((Cat) animal).shout();
	}
	private void shout() {
		System.out.println("Meow");
	}
}
