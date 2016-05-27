package chapter2;

public class ConstantTest {
	public final int i;

	// This IS NOT constant, as it can change with instance, as demonstrated
	// below
	public ConstantTest(int i) {
		this.i = i;
	}

	public static final int J;
	// This IS constant, as it cannot change its value for the rest of its life!
	static {
		J = 10;
	}
}
