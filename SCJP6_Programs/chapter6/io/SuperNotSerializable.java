package chapter6.io;

import java.io.*;

class Animal {
	public int weight;
}

class Dog extends Animal implements Serializable {
	
	private static final long serialVersionUID = -461741650975120876L;

	private String name;

	public void setName(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}
}

public class SuperNotSerializable {
	private Dog dog;

	public static void main(String[] args) {
		SuperNotSerializable s = new SuperNotSerializable();
		s.dog = new Dog();
		s.dog.setName("Dalmatian", 50);
		System.out.println("-------------------------------");
		System.out.println("BEFORE SERIALIZATION");
		System.out.println("Name: " + s.dog.getName());
		System.out.println("Weight:  " + s.dog.getWeight());
		System.out.println("-------------------------------");
		s.serialize();
		s.deserialize();
	}

	private void serialize() {
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(
					"d:\\1.ser"));
			o.writeObject(dog);
			o.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void deserialize() {
		try {
			ObjectInputStream i = new ObjectInputStream(new FileInputStream(
					"d:\\1.ser"));
			dog = (Dog) i.readObject();
			System.out.println("AFTER SERIALIZATION");
			System.out.println("Name: " + dog.getName());
			System.out.println("Weight: " + dog.getWeight());
			System.out.println("-------------------------------");
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}
