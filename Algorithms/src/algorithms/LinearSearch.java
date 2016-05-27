package algorithms;

public class LinearSearch {

	public static int findElement(Object[] inputArray, Object elementToFind) {
		int index = -1;
		try {
			for (int i = 0; i < inputArray.length; i++) {
				if (inputArray[i] != null
						&& inputArray[i].equals(elementToFind)) {
					index = i;
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return index;
	}
	
}
