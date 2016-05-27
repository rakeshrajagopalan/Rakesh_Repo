package com.datastructures;

import java.io.Serializable;

/**
 ** Old HashMap - For 100,000 records - 414360 ms. New HashMap - For 100,000
 * records - 266 ms. For 10,00,000 records - 14906 ms(2 GB RAM), 5319 ms(3 GB
 * RAM), 4050 ms(AMD Phenom II X6, 16 GB RAM), 2449 ms(Intel Core i7, 4 GB RAM).
 */

public class HashMap implements Serializable {

	private static final long serialVersionUID = -8738091971095453202L;

	private static final int CONSTANT_1 = 10;

	private static final int CONSTANT_2 = 32;

	private static final int AUGMENT_CAPACITY = 50;

	private static final int BUCKET_CUTOFF_SIZE = 1024;

	private Object[][] objects;

	private int hashValue;

	private int size;

	private class Entry {

		private Object key;

		private Object value;

		private Entry(Object key, Object value) {
			this.key = key;
			this.value = value;
		}

	}

	public HashMap() {
		objects = new Object[BUCKET_CUTOFF_SIZE][];
	}

	public void put(Object key, Object value) {
		if (key != null) {
			generateHash(key.hashCode());
			Object[] currentBucket = objects[hashValue];
			if (currentBucket == null) {
				objects[hashValue] = new Object[AUGMENT_CAPACITY];
			} else {
				if (objects[hashValue][objects[hashValue].length - 1] != null) {
					resizeArray();
				}
			}
			Entry entry = new Entry(key, value);
			int indexToInsert = 0;
			int midPoint = objects[hashValue].length / 2;
			Object tempObj = objects[hashValue][midPoint];
			if (tempObj == null) {
				for (int i = midPoint; i >= 0; i--) {
					if (objects[hashValue][i] != null) {
						indexToInsert = i + 1;
						break;
					}
				}
			} else {
				for (int i = midPoint; i < objects[hashValue].length; i++) {
					if (objects[hashValue][i] == null) {
						indexToInsert = i;
						break;
					}
				}
			}
			if (!checkForDuplicate(entry)) {
				objects[hashValue][indexToInsert] = entry;
				size += 1;
			}
		} else {
			throw new NullPointerException();
		}
	}

	private void resizeArray() {
		Object[] tempArray = new Object[(objects[hashValue].length) * 2];
		int position = 0;
		for (Object object : objects[hashValue]) {
			tempArray[position] = object;
			position += 1;
		}
		objects[hashValue] = tempArray;
	}

	private boolean checkForDuplicate(Entry entry) {
		Object[] currentBucket = objects[hashValue];
		boolean duplicateOccured = false;
		int index = 0;
		for (Object obj : currentBucket) {
			if (obj != null) {
				Entry ent = (Entry) obj;
				if (entry.key.equals(ent.key)) {
					objects[hashValue][index] = entry;
					duplicateOccured = true;
					break;
				}
				index += 1;
			} else {
				break;
			}
		}
		return duplicateOccured;
	}

	public Object get(Object key) {
		Object returnObject = null;
		if (key != null) {
			generateHash(key.hashCode());
			for (Object obj : objects[hashValue]) {
				if (obj != null) {
					Entry entry = (Entry) obj;
					if (entry.key.equals(key)) {
						returnObject = entry.value;
						break;
					}
				} else {
					break;
				}
			}
		} else {
			throw new NullPointerException();
		}
		return returnObject;
	}

	private int bucketSize() {
		Object[] currBucket = objects[hashValue];
		int midPoint = currBucket.length / 2;
		int bucketSize = 0;
		if (currBucket[midPoint] == null) {
			for (int i = 0; i < midPoint; i++) {
				if (currBucket[i] == null) {
					bucketSize = i;
				}
			}
		} else {
			for (int i = midPoint; i < currBucket.length; i++) {
				if (currBucket[i] == null) {
					bucketSize = i;
				}
			}
		}
		return bucketSize;
	}

	public void remove(Object key) {
		if (key != null) {
			generateHash(key.hashCode());
			if (objects[hashValue] != null) {
				int removedIndex = 0;
				int bucketSize = bucketSize();
				for (int i = 0; i < objects[hashValue].length; i++) {
					Object nextObject = objects[hashValue][i];
					if (nextObject != null) {
						Entry entry = (Entry) nextObject;
						if (entry.key.equals(key)) {
							objects[hashValue][i] = null;
							removedIndex = i;
							size -= 1;
							break;
						}
					}
				}
				if (removedIndex != (bucketSize - 1)) {
					objects[hashValue][removedIndex] = objects[hashValue][(bucketSize - 1)];
					objects[hashValue][(bucketSize - 1)] = null;
				}
			}
		} else {
			throw new NullPointerException();
		}
	}

	public void clear() {
		for (int i = 0; i < objects.length; i++) {
			if (objects[i] != null) {
				for (int j = 0; j < objects[i].length; j++) {
					objects[i][j] = null;
				}
				objects[i] = null;
			}
		}
		size = 0;
	}

	/**
	 * This uses middle-square method hashing. This method is guarantee to
	 * return a hash-value between 0 and 1024.
	 * 
	 * @param hashCode
	 */
	private void generateHash(int hashCode) {
		hashValue = (hashCode * hashCode) >>> (CONSTANT_2 - CONSTANT_1);
	}

	public int size() {
		return size;
	}

	public static void main(String[] args) {
		HashMap map = new HashMap();
		long start = System.currentTimeMillis();
		for (int i = 1; i <= 1000000; i++) {
			map.put(i, i);
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		// start = System.currentTimeMillis();
		// map.put("Apple", "A");
		// end = System.currentTimeMillis();
		// System.out.println("Time for put: " + (end - start));
		// start = System.currentTimeMillis();
		// map.remove(89507);
		// end = System.currentTimeMillis();
		// System.out.println("Time for remove: " + (end - start));
		// start = System.currentTimeMillis();
		// System.out.println(map.get(477866));
		// end = System.currentTimeMillis();
		// System.out.println("Time for get: " + (end - start));
		// char[] chars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
		// 'k',
		// 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
		// 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
		// 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
		// 'V', 'W', 'X', 'Y', 'Z', '!', '@', '#', '$', '%', '^', '&',
		// '*', '(', ')', '_', '-', '+', '=', '[', '{', '}', ']', '|',
		// '<', ',', '>', '.', '?', '/', '"' };
		// int constant = chars.length;
		// long start = System.currentTimeMillis();
		// for (int i = 0; i < 500000; i++) {
		// char[] temp = new char[10];
		// temp[0] = chars[(int) (Math.random() * constant)];
		// temp[1] = chars[(int) (Math.random() * constant)];
		// temp[2] = chars[(int) (Math.random() * constant)];
		// temp[3] = chars[(int) (Math.random() * constant)];
		// temp[4] = chars[(int) (Math.random() * constant)];
		// temp[5] = chars[(int) (Math.random() * constant)];
		// temp[6] = chars[(int) (Math.random() * constant)];
		// temp[7] = chars[(int) (Math.random() * constant)];
		// temp[8] = chars[(int) (Math.random() * constant)];
		// temp[9] = chars[(int) (Math.random() * constant)];
		// String s = new String(temp);
		// map.put(s, i);
		// }
		// long end = System.currentTimeMillis();
		// System.out.println(end - start);
	}

}
