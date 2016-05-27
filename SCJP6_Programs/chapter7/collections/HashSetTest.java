package chapter7.collections;

import java.util.*;

public class HashSetTest {
	public static void main(String[] args) {
		HashSet<String> test = new HashSet<String>();
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
