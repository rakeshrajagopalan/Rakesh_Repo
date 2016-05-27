package chapter7.collections;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

class CompTest implements Comparator<Integer> {
	public int compare(Integer o1, Integer o2) {
		return o2 - o1;
	}

}

public class BackedCollectionTest {
	public static void main(String[] args) {
		CompTest test = new CompTest();
		int[] ia = { 1, 3, 5, 6, 7, 8, 9 };
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i : ia) {
			pq.offer(i);
		}
		Iterator<Integer> it = pq.iterator();
		while (it.hasNext()) {
			System.out.print(it.next());
		}
		PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(10, test);
		for (int i : ia) {
			pq2.offer(i);
		}
		Iterator<Integer> it2 = pq2.iterator();
		System.out.println();
		while (it2.hasNext()) {
			System.out.print(it2.next());
		}
	}
}
