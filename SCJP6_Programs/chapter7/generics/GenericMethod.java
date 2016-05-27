package chapter7.generics;

import java.util.ArrayList;

class GenMethod {
	public <T> void genMethod(T type) {
		ArrayList<T> list = new ArrayList<T>();
		list.add(type);
	}
}

public class GenericMethod {
	public static void main(String[] args) {
		GenMethod genMethod = new GenMethod();
		genMethod.genMethod(new Dog());
	}
}
