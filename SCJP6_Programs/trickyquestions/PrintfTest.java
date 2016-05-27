package trickyquestions;

public class PrintfTest {
	public static void main(String[] args) {
		float f = 12345.456f;
		System.out.printf("%5.0f", f);// Total field width is 5, and 0 decimal
										// places should be in its right.
		System.out.printf("\n%5.1f", f);
		System.out.printf("\n%s",f);// %s calls the toString() of an Object
	}
}
