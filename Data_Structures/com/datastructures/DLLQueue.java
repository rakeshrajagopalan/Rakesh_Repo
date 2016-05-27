package com.datastructures;

import java.io.Serializable;

/**
 * Doubly Linked-List implementation of Queue (FIFO)
 * 
 * @author Rakesh
 * 
 */

public class DLLQueue implements Serializable {

	private static final long serialVersionUID = -3855919359430543346L;

	private Link currentLink;

	private Link frontPointer;

	private int size;

	private class Link {

		private int element;

		private Link previousLink;

		private Link nextLink;

		private Link(int element) {
			this.element = element;
		}

		private void display() {
			System.out.println(element);
		}

	}

	public void offer(int element) {
		Link tempLink = new Link(element);
		tempLink.previousLink = currentLink;
		if (currentLink == null) {
			frontPointer = tempLink;
		}
		if (tempLink.previousLink != null) {
			tempLink.previousLink.nextLink = tempLink;
		}
		currentLink = tempLink;
		size += 1;
	}

	public void display() {
		Link tempLink = frontPointer;
		while (tempLink != null) {
			tempLink.display();
			tempLink = tempLink.nextLink;
		}
	}

	public void delete(int element) {
		if (frontPointer != null) {
			Link tempLink = frontPointer;
			while (tempLink != null) {
				if (element == tempLink.element) {
					if (tempLink.previousLink == null) {
						frontPointer = tempLink.nextLink;
					} else if (tempLink.nextLink == null) {
						currentLink = tempLink.previousLink;
						tempLink.previousLink.nextLink = null;
					} else {
						tempLink.previousLink.nextLink = tempLink.nextLink;
						tempLink.nextLink.previousLink = tempLink.previousLink;
					}
					size -= 1;
				}
				tempLink = tempLink.nextLink;
			}
		}
	}

	public void sort() {
		int tracker = 0;
		Link temp;
		while ((temp = getLinkAt(tracker)).nextLink != null) {
			Link temp2 = temp.nextLink;
			if (temp2.nextLink == null) {
				tracker += 1;
				continue;
			}
			sort(temp, temp2);
			temp = temp.nextLink;
		}
	}

	private void sort(Link link1, Link link2) {
		if (link1.element > link2.element) {
			if (link1.previousLink == null) {
				frontPointer = link2;
			}
			Link temp = link1;
			link1.nextLink = link2.nextLink;
			link2.nextLink = temp;
			link1.previousLink = link2;
			link2.previousLink = temp.previousLink;
		} else if (link1.element < link2.element) {
			if (link2.nextLink == null) {
				currentLink = link1;
			}
			Link temp = link2;
			link2.previousLink = link1.previousLink;
			link1.previousLink = temp;
			link2.nextLink = link1;
			link1.previousLink = temp.previousLink;
		}
	}

	private Link getLinkAt(int position) {
		Link returnVal = frontPointer;
		if (position >= size) {
			return null;
		} else {
			for (int i = 0; i < position; i++) {
				returnVal = returnVal.nextLink;
			}
		}
		return returnVal;
	}

	public void reverse() {
		Link temp = currentLink;
		frontPointer = temp;
		while (temp.previousLink != null) {
			temp.nextLink = temp.previousLink;
			temp.previousLink.nextLink = temp;
			temp = temp.previousLink;
		}
		temp.nextLink = null;
		currentLink = temp;
	}

	public void poll() {
		frontPointer = frontPointer.nextLink;
		size -= 1;
	}

	public void peek() {
		frontPointer.display();
	}

	public void size() {
		System.out.println(size);
	}

	public static void main(String[] args) {
		DLLQueue queue = new DLLQueue();
		/*
		 * queue.offer(30); queue.offer(10); queue.offer(5); queue.offer(20);
		 * queue.offer(30); queue.offer(50); queue.offer(30); queue.display();
		 * queue.reverse(); queue.size();
		 * System.out.println("*****************"); queue.delete(30);
		 * queue.display(); queue.size();
		 */
		queue.offer(10);
		queue.offer(5);
		queue.offer(3);
		queue.offer(13);
		queue.sort();
		queue.display();
	}

}
