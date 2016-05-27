package algorithms;

public class MergeSort {

	private int[] array1;

	private int[] array2;

	private int[] array3;

	public MergeSort(int[] array1, int[] array2) {
		this.array1 = array1;
		this.array2 = array2;
		array3 = new int[array1.length + array2.length];
	}

	public void merge() {
		if (array1.length <= array2.length) {
			compare(array1, array2);
		} else {
			compare(array2, array1);
		}
	}

	private void compare(int[] array1, int[] array2) {
		int counter = 0;
		for (int i = 0, j = 0;;) {
			if (i < array1.length) {
				if (array1[i] > array2[j]) {
					array3[counter++] = array2[j++];
				} else {
					array3[counter++] = array1[i++];
				}
			} else if (j < array2.length) {
				for (int x = j; x < array2.length;) {
					array3[counter++] = array2[x++];
					j += 1;
				}
			} else {
				break;
			}
		}
	}

	public static void main(String[] args) {
		MergeSort ms = new MergeSort(new int[] { 1, 3, 4, 6, 7, 8, 13, 16, 19,
				23, 25 }, new int[] { 2, 3, 5, 8, 9, 10, 11, 13, 14, 30, 31,
				32, 45, 67, 78, 99, 102, 103, 105, 113 });
		ms.merge();
		for (int i : ms.array3) {
			System.out.print(i + " ");
		}
	}
}