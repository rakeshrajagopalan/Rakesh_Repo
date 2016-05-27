package chapter7.collections;

import java.util.*;

public class TreeMapTest {
	public static void main(String[] args) {
		int count = 0;
		TreeMap<Integer, String> test = new TreeMap<Integer, String>();
		for (String alphabet : Constant.values) {
			test.put(Constant.keys[count], alphabet);
			count += 1;
		}
		System.out.println("The keys are:");
		Set<Integer> keys = test.keySet();
		Iterator<Integer> keyIterator = keys.iterator();
		while (keyIterator.hasNext()) {
			System.out.print(keyIterator.next() + " ");
		}
		System.out.println("\nThe values are: ");
		Collection<String> collection = test.values();
		Iterator<String> iterator = collection.iterator();
		count = 0;
		while (iterator.hasNext()) {
			count += 1;
			System.out.print(iterator.next() + " ");
		}
	}
}
