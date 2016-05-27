package chapter2;

public class This extends Super {
	public This(String s) {
		super(s);// Since the super constructor takes Object, you must invoke
					// super with an argument of either Object, or its sub-type.
	}
}
