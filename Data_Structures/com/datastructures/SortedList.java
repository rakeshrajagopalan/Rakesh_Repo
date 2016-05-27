package com.datastructures;

import java.io.Serializable;

/**
 * 
 * @author rakrajagopalan
 * 
 */

public class SortedList implements Serializable {

	private static final long serialVersionUID = 3608510985083869538L;

	private Link first;

	private Link last;

	private class Link {

		private int data;

		private Link prev;

		private Link(int data) {
			this.data = data;
		}

		private void display() {
			System.out.print(data + " ");
		}

	}

	public void add(int data) {
		Link temp = new Link(data);
		Link temp2 = last;
		Link temp3 = null;
		if (last == null) {
			// Adding first element of the list.
			last = temp;
			first = temp;
		} else {
			if (temp.data < last.data) {
				// Checking the element to be added is lesser than 'last'
				// pointer. If yes, 'last' will be replaced with this data.
				// For example assume the list contains
				// 10(first),9,6,5(last) and now we want to add 4. Now,
				// 'last' pointer will be replaced with 4 and the list
				// will be 10,9,6,5,4.
				temp.prev = last;
				last = temp;
			} else if (temp.data > first.data) {
				// Checking whether the element to be added is greater than
				// the 'first' pointer. For example assume the list contains
				// 10(first),9,6,5(last) and now we want to add 17. Now,
				// 'first' pointer will be replaced with 17 and the list
				// will be 17,10,9,6,5.
				first.prev = temp;
				first = temp;
			} else {
				while (true) {
					if (temp2.data > temp.data) {
						// Assume the list contains 10,9,6,5 and element to be
						// added is 7. When it reaches the link of 9, this
						// condition will be satisfied. By then, temp will be 7,
						// temp2 will be 9 and temp3 will be 6. So, the new
						// order will be 10,9,7,6,5.
						temp.prev = temp2;
						temp3.prev = temp;
						break;
					} else {
						// This condition will simply move the temp2 to its
						// previous pointer.
						temp3 = temp2;
						temp2 = temp2.prev;
						continue;
					}
				}
			}
		}
	}

	public void display() {
		Link temp = last;
		while (temp != null) {
			temp.display();
			temp = temp.prev;
		}
	}

	public static void main(String[] args) {
		SortedList list = new SortedList();
		list.add(10);
		list.add(5);
		list.add(8);
		list.add(9);
		list.add(7);
		list.add(17);
		list.add(14);
		list.add(2);
		list.add(4);
		list.display();
	}

}