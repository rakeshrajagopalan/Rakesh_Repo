package chapter3;

public class StringReversal_2 {
	public static void main(String[] args) {
		char[] data = { 'a', 'b', 'c', 'd' };
		int midpoint = data.length / 2;
		for (int i = 0, j = data.length - 1; i < midpoint; i++, j--) {
			char temp = data[i];
			data[i] = data[j];
			data[j] = temp;
		}
		System.out.println(data);
	}
}
