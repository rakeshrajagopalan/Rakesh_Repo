package trickyquestions;

public class ReturnTest {
	public static void main(String[] args) {
		try {
			System.out.println(10/0);
		} catch(Exception ex) {
			System.out.println("Catch");
			return;
		} finally {
			System.out.println("Finally");
		}
	}
}
