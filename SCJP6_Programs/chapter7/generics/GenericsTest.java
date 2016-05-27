package chapter7.generics;

import java.util.*;

public class GenericsTest {
	public static void main(String[] args) {
		GenericsTest genTest = new GenericsTest();
		List<Object> list = new ArrayList<Object>();
		list.add("Apple");
		list.add("Boy");
		genTest.addToList(list);
		System.out.println("---------------------");
		System.out.println(list.get(3));
	}
	private void addToList(List<? super Object> list) {
		list.add("Cat");
		list.add(5);
	}
}
