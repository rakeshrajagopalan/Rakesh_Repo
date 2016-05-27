package chapter7.collections;

import java.util.*;

public class ArrayListTest {
	public static void main(String[] args) {
		ArrayList<String> test = new ArrayList<String>();
		for (String alphabet : Constant.values) {
			test.add(alphabet);
		}
		Iterator<String> iterator = test.iterator();
		int count = 0;
		while (iterator.hasNext()) {
			count += 1;
			System.out.println(count + " " + iterator.next());
		}
		ArrayList<String> test1 = new ArrayList<String>();
		test1.add("Cat");
		test1.add("Zebra");
		test1.add("XMas");
		test.retainAll(test1);
		for (String s : test) {
			System.out.println(s);
		}
	}
}
