package com.datastructures;

import java.io.Serializable;

public class Tree2 implements Serializable {

	private class TreeException extends Exception {

		private static final long serialVersionUID = 3974632097768572182L;

		private TreeException(String description) {
			System.out.println(description);
		}

	}

	private static final long serialVersionUID = -1726781390814535480L;

	private Object[] data;

	private Object[] leftChild;

	private Object[] rightChild;

	private static final int INITIAL_SIZE = 50;

	private int currentIndex = 0;

	public Tree2() {
		data = new Object[INITIAL_SIZE];
		leftChild = new Object[INITIAL_SIZE];
		rightChild = new Object[INITIAL_SIZE];
		for (int i = 0; i < leftChild.length; i++) {
			leftChild[i] = -1;
			rightChild[i] = -1;
		}
	}

	public void insert(int element) throws TreeException {
		if (element > 0) {
			if (data[0] == null) {
				data[0] = element;
				currentIndex += 1;
			} else {
				if (data[(data.length - 1)] != null) {
					resizeArray(element);
				} else {
					data[currentIndex] = element;
					currentIndex += 1;
					insertData(element);
				}
			}
		} else {
			throw new TreeException("Data cannot be negative");
		}
	}

	private void resizeArray(int element) throws TreeException {
		Object[] temp = data;
		int count = data.length;
		data = new Object[data.length + INITIAL_SIZE];
		int counter = 0;
		for (Object object : temp) {
			data[counter] = object;
			counter++;
		}
		temp = leftChild;
		leftChild = new Object[leftChild.length + INITIAL_SIZE];
		counter = 0;
		for (Object object : temp) {
			leftChild[counter] = object;
			counter++;
		}
		temp = rightChild;
		rightChild = new Object[rightChild.length + INITIAL_SIZE];
		counter = 0;
		for (Object object : temp) {
			rightChild[counter] = object;
			counter++;
		}
		for (int i = count; i < leftChild.length; i++) {
			leftChild[i] = -1;
			rightChild[i] = -1;
		}
		insert(element);
	}

	private void insertData(int element) {
		int temp = 0;
		OUTER: while (data[temp] != null) {
			if (element >= ((Integer) data[temp])) {
				if ((Integer) rightChild[temp] == -1) {
					rightChild[temp] = currentIndex;
					temp += 1;
					break OUTER;
				} else {
					temp += 1;
					continue OUTER;
				}
			} else {
				if ((Integer) leftChild[temp] == -1) {
					leftChild[temp] = currentIndex;
					temp += 1;
					break OUTER;
				} else {
					temp += 1;
					continue OUTER;
				}
			}
		}
	}

	public int size() {
		return currentIndex;
	}

	// public int[] getSortedArray() {
	// int[] returnArray = new int[size()];
	//		
	// }

	private void traverseTree() {

	}

	public static void main(String[] args) throws Exception {
		Tree2 t = new Tree2();
		t.insert(5);
		t.insert(10);
		t.insert(7);
		t.insert(13);
		t.insert(5);
		t.insert(7);
		t.insert(3);
		for (Object o : t.leftChild) {
			if ((Integer) o != -1)
				System.out.print((Integer) o + " ");
		}
		System.out.println();
		for (Object o : t.rightChild) {
			if ((Integer) o != -1)
				System.out.print((Integer) o + " ");
		}
		// int[] sortedArray = new int[t.data.length];
		// int index = 0;
		// for (Object i : t.data) {
		// int x = (Integer) i;
		// sortedArray[index] = x;
		// index++;
		// }

	}

}
