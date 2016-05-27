package chapter7.collections;

import java.util.*;

public class PriorityQueueTest {
	public static void main(String[] args) {
		PriorityQueue<String> test = new PriorityQueue<String>();
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
