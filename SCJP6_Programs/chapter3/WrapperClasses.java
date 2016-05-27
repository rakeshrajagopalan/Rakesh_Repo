package chapter3;

public class WrapperClasses {
	public static void main(String[] args) {
		System.out.println(Integer.valueOf("011",8));//NumberFormat
		System.out.println(Integer.toOctalString(63)); //77
		System.out.println(Integer.toBinaryString(63)); //111111
		System.out.println(Integer.toHexString(63));//3f
		System.out.println(Integer.decode("0X3f"));
		Integer i = 30;
		System.out.println(i.intValue());
		Integer ii = Integer.parseInt("30");
		System.out.println(ii);
	}
}
