package algorithms;

public class BubbleSort {
	
	public static int[] bubbleSort(int[] inputArray) throws Exception {
		for (int i = 0; i < (inputArray.length - 1); i++) {
			for (int j = 0; j < ((inputArray.length - 1) - i); j++) {
				int firstElement = inputArray[j];
				int secondElement = inputArray[j + 1];
				if (firstElement > secondElement) {
					int temp = firstElement;
					inputArray[j] = secondElement;
					inputArray[j + 1] = temp;
				}
			}
		}
		return inputArray;
	}
	
}
