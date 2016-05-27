package algorithms;

public class BinarySearch {

	public static int findElement(int[] inputArray, int elementToFind) {
		int index = -1;
		try {
			int midPoint = inputArray.length / 2;
			inputArray = BubbleSort.bubbleSort(inputArray);
			if (elementToFind < inputArray[midPoint]) {
				for (int i = 0; i < midPoint; i++) {
					if (inputArray[i] == elementToFind) {
						index = i;
						break;
					}
				}
			} else {
				for (int i = midPoint; i < inputArray.length; i++) {
					if (inputArray[i] == elementToFind) {
						index = i;
						break;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return index;
	}

}
