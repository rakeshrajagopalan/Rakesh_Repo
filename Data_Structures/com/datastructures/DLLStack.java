package com.datastructures;

import java.io.Serializable;

/**
 * Doubly Linked-List implementation of a Stack (LIFO)
 * 
 * @author Rakesh
 * 
 */

public class DLLStack implements Serializable, Iterator<Integer> {

	private static final long serialVersionUID = 1627872483996825340L;

	private Link currentLink;

	private int size;

	private int currentPointer;

	private Link currentIteratorLink;

	private class Link {

		private int element;

		private Link previousLink;

		private Link nextLink;

		private Link(int element) {
			this.element = element;
		}

		private void display() {
			System.out.print(element + " ");
		}

	}

	public void push(int element) {
		Link tempLink = new Link(element);
		tempLink.previousLink = currentLink;
		if (tempLink.previousLink != null) {
			tempLink.previousLink.nextLink = tempLink;
		}
		currentLink = tempLink;
		size += 1;
	}

	public void display() {
		Link tempLink = currentLink;
		while (tempLink != null) {
			tempLink.display();
			if (tempLink.previousLink != null) {
				tempLink = tempLink.previousLink;
			} else {
				tempLink = null;
			}
		}
	}

	public void delete(int element) {
		Link tempLink = currentLink;
		while (tempLink != null) {
			if (tempLink.element == element) {
				size -= 1;
				if (tempLink.previousLink == null && tempLink.nextLink == null) {
					tempLink = null;
					currentLink = null;
					break;
				} else if (tempLink.previousLink == null) {
					tempLink.nextLink.previousLink = null;
					tempLink = tempLink.nextLink;
					tempLink.previousLink = null;
					break;
				} else if (tempLink.nextLink == null) {
					tempLink = tempLink.previousLink;
					tempLink.nextLink.previousLink = null;
					tempLink.nextLink = null;
					currentLink = tempLink;
					break;
				} else {
					tempLink.nextLink.previousLink = tempLink.previousLink;
					tempLink.previousLink.nextLink = tempLink.nextLink;
					break;
				}
			} else {
				tempLink = tempLink.previousLink;
				continue;
			}
		}
	}

	public void add(int index, int element) {
		if (index < size) {
			size += 1;
			Link newLink = new Link(element);
			Link temp = currentLink;
			for (int i = 0; i <= index; i++) {
				if (i == index) {
					break;
				} else {
					temp = temp.previousLink;
				}
			}
			Link tempNext = temp.nextLink;
			temp.nextLink = newLink;
			tempNext.previousLink = newLink;
			newLink.previousLink = temp;
			newLink.nextLink = tempNext;
		} else {
			throw new IllegalArgumentException("Index out of range");
		}
	}

	public int get(int index) {
		Link temp = currentLink;
		int returnElement = -1;
		if (index < size) {
			for (int i = 0; i <= index; i++) {
				if (i == index) {
					returnElement = temp.element;
				}
				temp = temp.previousLink;
			}
		} else {
			throw new IllegalArgumentException("Index out of range");
		}
		return returnElement;
	}

	public int pop() {
		int value = 0;
		if (currentLink != null) {
			value = currentLink.element;
			currentLink.previousLink.nextLink = null;
			currentLink = currentLink.previousLink;
			size -= 1;
		}
		return value;
	}

	public void size() {
		System.out.println(size);
	}

	public boolean hasNext() {
		boolean hasNext = false;
		if (currentPointer < size) {
			hasNext = true;
		}
		return hasNext;
	}

	public Integer next() {
		int nextVal = -1;
		try {
			if (currentIteratorLink == null) {
				currentIteratorLink = currentLink;
				currentIteratorLink.previousLink = currentLink.previousLink;
				currentIteratorLink.nextLink = currentLink;
			} else {
				Link tempLink = currentIteratorLink;
				currentIteratorLink = currentIteratorLink.previousLink;
				currentIteratorLink.nextLink = tempLink;
			}
			nextVal = currentIteratorLink.element;
			currentPointer++;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return nextVal;
	}

	public Iterator<Integer> iterator() {
		return this;
	}

	public static void main(String[] args) {
		DLLStack stack = new DLLStack();
		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.push(40);
		stack.push(50);
		stack.push(60);
		stack.push(70);
		stack.push(80);
		stack.push(90);
		// stack.display();
		Iterator<Integer> it = stack.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
	}

}
