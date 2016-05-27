package chapter6.io;

import java.io.*;

class Fruit {

	private int price;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}

class Mango extends Fruit implements Serializable {

	private static final long serialVersionUID = -4864525939649558005L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

public class ArraySerializationTest {

	private Fruit[] fruits;

	public static void main(String[] args) {
		ArraySerializationTest ast = new ArraySerializationTest();
		Mango mango1 = new Mango();
		mango1.setPrice(15);
		Fruit fruit = new Fruit();
		fruit.setPrice(10);
		Mango mango2 = new Mango();
		mango2.setPrice(25);
		ast.fruits = new Fruit[] { mango1, fruit, mango2 };
		ast.serialize();
		ast.deserialize();
	}

	private void serialize() {
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(
					"d:\\Array.ser"));
			o.writeObject(fruits);
			o.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void deserialize() {
		try {
			ObjectInputStream i = new ObjectInputStream(new FileInputStream(
					"d:\\Array.ser"));
			fruits = (Fruit[]) i.readObject();
			for (Fruit m : fruits) {
				System.out.println("Price is: " + m.getPrice());
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

}
