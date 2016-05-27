package com.datastructures;

import java.io.Serializable;

/**
 * Incomplete
 * @author Rakesh
 *
 * @param <E>
 */

public class Deque<E extends Object> implements Serializable {

	private static final long serialVersionUID = 6000026642235925634L;

	private class Link {

		private E data;

		private Link nextLink;

		private Link(E data) {
			this.data = data;
		}

	}

	private Link frontPointer;

	private Link rearPointer;

	private int size;

	public void offerFirst(E e) {
		Link newLink = new Link(e);
		if (frontPointer == null) {
			frontPointer = rearPointer = newLink;
		} else {
			Link tempLink = frontPointer;
			tempLink.nextLink = newLink;
			frontPointer = newLink;
		}
		size += 1;
	}

	public void offerLast(E e) {
		Link newLink = new Link(e);
		if (rearPointer == null) {
			frontPointer = rearPointer = newLink;
		} else {
			Link tempLink = rearPointer;
			tempLink.nextLink = newLink;
			rearPointer = newLink;
		}
		size += 1;
	}

	public E peekFirst() {
		return frontPointer.data;
	}

	public E peekLast() {
		return rearPointer.data;
	}

	public E pollFirst() {
		return null;
	}

	public E pollLast() {
		return null;
	}

	public int size() {
		return size;
	}
	
	public static void main(String[] args) {
		Deque<String> deque = new Deque<String>();
		deque.offerFirst("Apple");
		deque.offerFirst("Boy");
		deque.offerFirst("Zebra");
		System.out.println(deque.peekFirst());
		System.out.println(deque.peekLast());
	}

}
