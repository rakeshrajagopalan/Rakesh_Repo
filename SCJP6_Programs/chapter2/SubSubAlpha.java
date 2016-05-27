package chapter2;

class Alpha {
	static String s = "";
	protected Alpha() {
		s += "Alpha ";
	}
	static void test(){};
}

class SubAlpha extends Alpha {
	private SubAlpha() {
		s += "Sub ";
	}
	 void sample() {
		test();
	}
}

public class SubSubAlpha extends Alpha {
	private SubSubAlpha() {
		s+= "Subsub";
	}
	public static void main(String[] args) {
		new SubSubAlpha();
		System.out.println(s);
	}
}
