package chapter3;

public class BitTest {
	public static void main(String[] args) {
		short l = 320; //BIT PATTERN: 0000 0001 0100 0000
		byte b = (byte) l; //NEW BIT PATTERN: 0100 0000
		System.out.println(b);// O/P: 64
	}
}
