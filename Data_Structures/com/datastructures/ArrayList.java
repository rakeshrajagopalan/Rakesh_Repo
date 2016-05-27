package com.datastructures;

import java.io.Serializable;

public class ArrayList<E extends Object> implements Serializable, Iterator<E> {

	private static final long serialVersionUID = -737306987338472884L;

	private Object[] objects;

	private int initialCapacity = 10;

	private int lastIndex = 0;

	private int currentPointer;

	private static final String ADD_REQ = "ADD_REQ";

	private static final String DEL_REQ = "DEL_REQ";

	public ArrayList() {
		objects = new Object[initialCapacity];
	}

	public void add(E o) {
		checkCapacity();
		objects[lastIndex] = o;
		lastIndex += 1;
	}

	public Object[] toArray() {
		Object[] toReturn = new Object[size()];
		int temp = 0;
		for (int i = 0; i < objects.length; i++) {
			Object o = objects[i];
			if (o != null) {
				toReturn[temp] = o;
				temp += 1;
			}
		}
		return toReturn;
	}

	public int size() {
		return lastIndex;
	}

	@SuppressWarnings("unchecked")
	public E get(int index) {
		E obj = null;
		if (index <= size()) {
			try {
				obj = (E) objects[index];
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			throw new IndexOutOfBoundsException();
		}
		return obj;
	}

	public void delete(int index) {
		if (!(index > objects.length)) {
			compactList(index, DEL_REQ);
		}
	}

	public void add(int index, E object) {
		compactList(index, ADD_REQ);
		objects[index] = object;
	}

	private void compactList(int index, String requestType) {
		if (requestType.equals(DEL_REQ)) {
			objects[index] = null;
			for (int i = index; i < lastIndex; i++) {
				objects[i] = objects[i + 1];
			}
			lastIndex -= 1;
		} else {
			if (objects[(objects.length - 1)] != null) {
				checkCapacity();
			}
			for (int i = lastIndex; i >= index; i--) {
				objects[i + 1] = objects[i];
			}
			lastIndex += 1;
		}
	}

	public void clear() {
		for (int i = 0; i < objects.length; i++) {
			objects[i] = null;
		}
		objects = null;
		lastIndex = 0;
		initialCapacity = 10;
	}

	private void checkCapacity() {
		if (lastIndex >= initialCapacity) {
			Object[] temp = objects;
			objects = new Object[initialCapacity + initialCapacity];
			for (int i = 0; i < temp.length; i++) {
				objects[i] = temp[i];
			}
			initialCapacity += initialCapacity;
		}
	}

	public Iterator<E> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		boolean hasNext = false;
		if (currentPointer < lastIndex) {
			hasNext = true;
		} else {
			currentPointer = 0;
		}
		return hasNext;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E next() {
		Object toReturn = objects[currentPointer];
		currentPointer += 1;
		return (E) toReturn;
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		System.out.println("com.datasrtuctures.ArrayList");
		System.out.println("----------------------------");
		ArrayList<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Boy");
		list.add("Cat");
		list.add("Dog");
		list.add("Eagle");
		list.add("Fish");
		list.add("Gun");
		list.add("House");
		list.add("Ink");
		list.add("Jug");
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		System.out.println("Size: " + list.size());
		list.add(3, "Zebra");
		iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		System.out.println("Size: " + list.size());
		System.out.println("\njava.util.ArrayList");
		System.out.println("--------------------");
		java.util.ArrayList<String> list1 = new java.util.ArrayList<String>();
		list1.add("Apple");
		list1.add("Boy");
		list1.add("Cat");
		list1.add("Dog");
		list1.add("Eagle");
		list1.add("Fish");
		list1.add("Gun");
		list1.add("House");
		list1.add("Ink");
		list1.add("Jug");
		java.util.Iterator<String> iterator1 = list1.iterator();
		while (iterator1.hasNext()) {
			System.out.print(iterator1.next() + " ");
		}
		System.out.println("Size: " + list1.size());
		list1.add(3, "Zebra");
		iterator1 = list1.iterator();
		while (iterator1.hasNext()) {
			System.out.print(iterator1.next() + " ");
		}
		System.out.println("Size: " + list1.size());
	}

}
