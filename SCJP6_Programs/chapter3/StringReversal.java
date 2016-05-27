package chapter3;

public class StringReversal {

	public String reverse(String original) {
		char[] reversed = new char[original.length()];
		int midPoint = original.length() / 2;
		for (int i = 0, j = (original.length() - 1); i <= midPoint; i++, j--) {
			reversed[i] = original.charAt(j);
			reversed[j] = original.charAt(i);
		}
		return new String(reversed);
	}

	public static void main(String[] args) {
		StringReversal reversor = new StringReversal();
		String original = "abcd";
		String[] tokens = original.split(" ");
		StringBuilder builder = new StringBuilder();
		for (String nextToken : tokens) {
			builder.append(reversor.reverse(nextToken));
			builder.append(" ");
		}
		System.out.println(builder.toString());
	}
}
