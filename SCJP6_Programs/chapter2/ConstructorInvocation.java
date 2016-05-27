package chapter2;

public class ConstructorInvocation {
	private String name;
	public ConstructorInvocation() {
		this(getName());
	}
	public ConstructorInvocation(String name) {
		super();
		this.name = name;
	}
	private static String getName() {
		int i = (int) (Math.random() * 3);
		String name = new String[]{"Apple","Banana","Cream"}[i];
		return name;
	}
	public static void main(String[] args) {
		ConstructorInvocation ci1 = new ConstructorInvocation("Milk");
		System.out.println(ci1.name);
		ConstructorInvocation ci2 = new ConstructorInvocation();
		System.out.println(ci2.name);
	}
}
