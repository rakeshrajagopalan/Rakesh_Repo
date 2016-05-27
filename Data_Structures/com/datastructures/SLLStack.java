package com.datastructures;

/**
 * Singly Linked-List implementation of a Stack (LIFO)
 * 
 * @author Rakesh
 * 
 */

public class SLLStack {

	private Link current;

	private int size;

	private class Link {

		private int data;

		private Link prev;

		private Link(int data) {
			this.data = data;
		}

		private void display() {
			System.out.println(data);
		}
	}

	public void add(int data) {
		if (current == null) {
			current = new Link(data);
		} else {
			Link temp = new Link(data);
			temp.prev = current;
			current = temp;
		}
		size++;
	}

	public void display() {
		if (size > 0) {
			Link temp = current;
			while (temp != null) {
				temp.display();
				temp = temp.prev;
			}
		}
	}

	public int size() {
		return size;
	}

	public void delete(int data) {
		Link temp = current;
		Link temp2 = null;
		while (temp != null) {
			if (temp.data == data) {
				size--;
				if (current.prev == null) {
					current = null;
					break;
				} else if (temp.prev == null) {
					temp2.prev = null;
					temp = null;
					break;
				} else {
					if (temp2 != null) {
						temp2.prev = temp.prev;
						temp.prev = null;
						break;
					} else {
						current = temp.prev;
						temp.prev = null;
						temp = null;
						break;
					}
				}
			} else {
				temp2 = temp;
				temp = temp.prev;
			}
		}
	}

	public static void main(String[] args) {
		SLLStack singlyLinkedListStack = new SLLStack();
		singlyLinkedListStack.add(10);
		singlyLinkedListStack.add(20);
		singlyLinkedListStack.add(30);
		singlyLinkedListStack.add(40);
		singlyLinkedListStack.add(50);
		// singlyLinkedListStack.display();
		// System.out.println("Size is: " + singlyLinkedListStack.size());
		singlyLinkedListStack.delete(30);
		singlyLinkedListStack.display();
		System.out.println("----------------------------");
		singlyLinkedListStack.delete(10);
		singlyLinkedListStack.display();
		System.out.println("----------------------------");
		singlyLinkedListStack.delete(50);
		singlyLinkedListStack.display();
		System.out.println("----------------------------");
		singlyLinkedListStack.delete(40);
		singlyLinkedListStack.display();
		System.out.println("----------------------------");
		singlyLinkedListStack.delete(20);
		singlyLinkedListStack.display();
		System.out.println("----------------------------");
	}

}
