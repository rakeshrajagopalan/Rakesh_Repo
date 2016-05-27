package alarmpack;

class Animal {
	public void eat() {
		System.out.println("Generic");
	}
}

class Horse extends Animal {
	public void eat() {
		System.out.println("Horse");
	}
}

public class Test2 {
	public static void main(String[] args) {
		((Animal)new Horse()).eat();
	}
}
