package com.datastructures;

import java.io.Serializable;

/**
 * Singly Linked-List Queue
 * 
 * @author Rakesh
 * 
 */

public class SLLQueue implements Serializable {

	private static final long serialVersionUID = 515778477093947640L;

	private Link frontPtr;

	private Link currentLink;

	private class Link {

		private int data;

		private Link nextLink;

		private Link(int data) {
			this.data = data;
		}

		private void display() {
			System.out.print(data + " ");
		}

	}

	public void add(int element) {
		if (frontPtr == null) {
			frontPtr = new Link(element);
			frontPtr.nextLink = currentLink;
			currentLink = frontPtr;
		} else {
			Link tempLink = new Link(element);
			currentLink.nextLink = tempLink;
			currentLink = tempLink;
		}
	}
	
	public void reverse() {
		Link current = frontPtr;
		frontPtr = null;
		while(current != null) {
			Link temp = current;
			current = current.nextLink;
			temp.nextLink = frontPtr;
			frontPtr = temp;
		}
	}
	
	public void delete(int data) {
		
	}

	public void display() {
		Link tempLink = frontPtr;
		while (tempLink != null) {
			tempLink.display();
			tempLink = tempLink.nextLink;
		}
	}

	public static void main(String[] args) {
		SLLQueue q2 = new SLLQueue();
		q2.add(5);
		q2.add(10);
		q2.add(15);
		q2.add(20);
		q2.add(25);
		q2.display();
		System.out.println();
		q2.reverse();
		q2.display();
	}

}
