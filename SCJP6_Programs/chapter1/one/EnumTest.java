package chapter1.one;

enum Day {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
} //NOTE: Enums declared outside the 

//class should not have any modifiers.
//Enums should not be declared inside the method.

class BeanClass {
	private Alphabet alphabet;

	private Day day;

	public Alphabet getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(Alphabet alphabet) {
		this.alphabet = alphabet;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

}

public class EnumTest {
	private enum IceCream {
		VANILLA, STRAWBERRY, MANGO, PISTA, CHOCOLATE, BUTTERSCOTCH
	};

	public static void main(String[] args) {
		BeanClass bc = new BeanClass();
		bc.setAlphabet(Alphabet.MAN);
		bc.setDay(Day.FRIDAY);
		System.out.println(bc.getAlphabet() + " ate " + IceCream.CHOCOLATE
				+ " on " + bc.getDay());
	}
}
