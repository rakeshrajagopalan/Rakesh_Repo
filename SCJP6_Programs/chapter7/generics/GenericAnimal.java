package chapter7.generics;

class GenericTest<T extends Animal> {
	public void eat(T animal) {
		animal.eat(animal);
	}
	public <X> X method(X t) {
		float f = 9.0F;
		return t;
	}
}

public class GenericAnimal {
	public static void main(String[] args) {
		Animal a = new Cat();
		GenericTest<Cat> cat = new GenericTest<Cat>();
		cat.eat((Cat)a);
	}
}
