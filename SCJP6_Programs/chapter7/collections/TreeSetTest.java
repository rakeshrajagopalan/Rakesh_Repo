package chapter7.collections;

import java.util.*;

public class TreeSetTest {
	public static void main(String[] args) {
		TreeSet<String> test = new TreeSet<String>();
		for (String alphabet : Constant.values) {
			test.add(alphabet);
		}
		Iterator<String> iterator = test.iterator();
		int count = 0;
		while (iterator.hasNext()) {
			count += 1;
			System.out.println(count + " " + iterator.next());
		}
	}
}
