package trickyquestions;

public class StaticInit2 {

	static {
		System.out.println("ABC");
	}
	
	public StaticInit2() {
		System.out.println("DEF");
	}
	
	public static void main(String[] args) {
		StaticInit2 si1 = new StaticInit2();
		StaticInit2 si2 = new StaticInit2();
	}
	
}
