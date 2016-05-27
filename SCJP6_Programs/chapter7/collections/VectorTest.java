package chapter7.collections;

import java.util.*;

public class VectorTest {
	public static void main(String[] args) {
		Vector<String> test = new Vector<String>();
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
