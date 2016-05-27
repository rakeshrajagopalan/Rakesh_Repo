package chapter6.io;

import java.io.*;

class Collar {
	private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}

class Cat implements Serializable {

	private static final long serialVersionUID = -461741650975120876L;

	private String name;

	private transient Collar collar;

	public Collar getCollar() {
		return collar;
	}

	public void setCollar(Collar collar) {
		this.collar = collar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private void writeObject(ObjectOutputStream o) {
		try {
			o.defaultWriteObject();
			o.writeInt(collar.getSize());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void readObject(ObjectInputStream i) {
		try {
			i.defaultReadObject();
			collar = new Collar();
			collar.setSize(i.readInt());
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

}

public class SerializationTest {

	private Cat cat;

	public static void main(String[] args) {
		SerializationTest test = new SerializationTest();
		test.cat = new Cat();
		test.cat.setName("Kiity");
		Collar collar = new Collar();
		collar.setSize(10);
		test.cat.setCollar(collar);
		test.serialize();
		test.deserialize();
	}

	private void serialize() {
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(
					"d:\\Cat.ser"));
			o.writeObject(cat);
			o.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void deserialize() {
		try {
			ObjectInputStream i = new ObjectInputStream(new FileInputStream(
					"d:\\Cat.ser"));
			cat = (Cat) i.readObject();
			System.out.println("Name: " + cat.getName());
			System.out.println("Collar Size: " + cat.getCollar().getSize());
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}
