package chapter9;

import java.util.ArrayList;
import java.util.List;

import chapter7.collections.NavigableSetTest;

class Animal {}

class Cat extends Animal {}

class Dog extends Animal {}

public class GenericsTest {
	public static void main(String[] args) {
		List<? super Dog> l = new ArrayList<Animal>();
		l.add((Dog) new Animal());
	}
}
