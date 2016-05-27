package chapter3;

public class IntTesting {
	public static void main(String[] args) {
		float i1 = 0213L;
		long i2 = 0213;
		System.out.println(i1);
		System.out.println(i2);
		byte a = 10;
		short b = 15;
		byte c = (byte)(b/a);
		int i =10;
		long l = 1234567890123456789L;
		float f = i;
		float f1 = l;
		double d = l;
		System.out.println(f1);
		System.out.println(d);
		long lo = 130L;
		byte byt = (byte)lo;
		System.out.println(byt);
	}
}
