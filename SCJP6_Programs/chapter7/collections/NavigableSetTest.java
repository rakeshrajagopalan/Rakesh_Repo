package chapter7.collections;

import java.util.Iterator;
import java.util.TreeSet;

public class NavigableSetTest {
	public static void main(String[] args) {
		TreeSet<Integer> treeSet = new TreeSet<Integer>();
		treeSet.add(1205);
		treeSet.add(1505);
		treeSet.add(1545);
		treeSet.add(1830);
		treeSet.add(2010);
		treeSet.add(2100);
		treeSet.add(1600);
		System.out.println("Lower: " + treeSet.lower(1600));
		System.out.println("Floor: " + treeSet.floor(1600));
		System.out.println(treeSet.pollFirst());
		System.out.println("-------------------------------");
		treeSet = (TreeSet<Integer>)treeSet.descendingSet();
		Iterator<Integer> iterator = treeSet.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
