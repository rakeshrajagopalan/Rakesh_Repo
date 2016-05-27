package trickyquestions;

class Person{}
class Employee extends Person {}
public class Test {
	public void print(Random e,Person p) {}
	public void print(Person e,Random p) {}
	public static void main(String[] args) {
		Random e = new Random();
		Person p = new Person();
		new Test().print(e, p);
	}
}
