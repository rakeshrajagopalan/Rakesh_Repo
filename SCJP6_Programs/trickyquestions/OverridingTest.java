package trickyquestions;

class One { 
	public Object one() {
		return new String("Base");
	}
}

class Two extends One {
	public String one() {
		return new String("Derived");
	}
}

public class OverridingTest {
	public static void main(String[] args) {
		One one = new Two();
		String s = (String)one.one();
		System.out.println(s);
	}
}
