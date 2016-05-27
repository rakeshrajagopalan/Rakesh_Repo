package chapter2;

public class Rice extends Food {
	public static void eat() {
		System.out.println("Rice");
	}
	public static void main(String[] args) {
		Food f = new Rice();
		f.eat(); //Food
		Rice r = new Rice();
		r.eat(); //Rice
		Food fo = new Food();
		fo.eat(); //Food
		r = (Rice)f; 
		r.eat(); //Rice
	}
}
