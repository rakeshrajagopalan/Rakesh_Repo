package dsapplications;

import algorithms.BubbleSort;

public class MergeArrays {

	private int[] array3;

	private int positionIndicator;

	private int pointer1;

	private int pointer2;

	private int pass;

	public MergeArrays() {
		try {
			int[] array1 = { 750, 450, 345, 900, 445 };
			int[] array2 = { 150, 250, 455, 600, 675, 756, 435, 467, 456, 123,
					97 };
			mergeArrays(array1, array2);
			System.out.print("The Merged array is: ");
			for (int i : array3) {
				System.out.print(i + " ");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void mergeArrays(int[] array1, int[] array2) throws Exception {
		array1 = BubbleSort.bubbleSort(array1);
		array2 = BubbleSort.bubbleSort(array2);
		array3 = new int[array1.length + array2.length];
		while (pass < array3.length) {
			if (pointer1 < array1.length && pointer2 < array2.length) {
				if (array1[pointer1] < array2[pointer2]) {
					array3[positionIndicator] = array1[pointer1];
					positionIndicator += 1;
					pointer1 += 1;
					pass += 1;
				} else {
					array3[positionIndicator] = array2[pointer2];
					positionIndicator += 1;
					pointer2 += 1;
					pass += 1;
				}
			} else {
				if (pointer1 < array1.length) {
					for (int i = pointer1; i < array1.length; i++) {
						array3[positionIndicator] = array1[i];
						positionIndicator += 1;
						pointer1 += 1;
						pass += 1;
					}
				} else {
					for (int i = pointer2; i < array2.length; i++) {
						array3[positionIndicator] = array2[i];
						positionIndicator += 1;
						pointer2 += 1;
						pass += 1;
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		new MergeArrays();
	}
}
