package chapter2;

public class PrimitivesCasting {
	public static void main(String[] args) {
		// For primitive conversion from higher bit capacity to lower bit
		// capacity(example, from double to byte), explicit casting is required.
		// If the value of the higher primitive exceeds the value of the smaller
		// one, JVM truncates the excessive value.
		double d = 13432.435;
		byte b = (byte) d;
		System.out.println(b);
		// For primitive conversion from lower bit capacity to higher bit
		// capacity(example, from byte to int), no explicit casting is required.
		int i = b;
		System.out.println(i);
	}
}
