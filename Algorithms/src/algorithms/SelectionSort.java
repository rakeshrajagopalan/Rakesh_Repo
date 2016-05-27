package algorithms;

public class SelectionSort {

	public static int[] selectionSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = (i + 1); j < array.length; j++) {
				if (array[i] > array[j]) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		return array;
	}
	
	public static void main(String[] args) {
		int[] elements = {7,4,1,2,6};
		elements = SelectionSort.selectionSort(elements);
		for(int next : elements) {
			System.out.print(next + " ");
		}
	}

}
