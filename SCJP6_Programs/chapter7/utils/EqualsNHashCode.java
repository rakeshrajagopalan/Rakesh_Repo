package chapter7.utils;

import java.util.HashMap;

class Dog {
	private String name;

	public Dog(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object obj) {
		if (((Dog) obj) == this) {
			return true;
		} else {
			return false;
		}
	}

	public int hashCode() {
		int value = 0;
		char[] chars = name.toCharArray();
		for (char ch : chars) {
			value += Integer.valueOf(ch);
		}
		return value;
	}
}

public class EqualsNHashCode {
	public static void main(String[] args) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		//Test 1
		map.put(new Dog("Tommy"), "Rakesh");
		System.out.println(map.get(new Dog("Tommy")));
		//Test 2
		map.clear();
		Dog dog = new Dog("Tommy");
		map.put(dog, "Rakesh");
		dog.setName("ymmoT");
		System.out.println(map.get(dog));
	}
}
