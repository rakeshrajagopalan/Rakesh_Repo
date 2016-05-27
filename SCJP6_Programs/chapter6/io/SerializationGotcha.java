package chapter6.io;

import java.io.*;

class SpecialSerial implements Serializable {
	transient int y = 7;
	static int z = 9;
}

public class SerializationGotcha {
	public static void main(String[] args) {
		SpecialSerial s = new SpecialSerial();
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(
					"d:\\Rakesh.ser"));
			o.writeObject(s);
			o.close();
			System.out.print(++s.z + " ");
			ObjectInputStream i = new ObjectInputStream(new FileInputStream(
					"d:\\Rakesh.ser"));
			SpecialSerial s2 = (SpecialSerial) i.readObject();
			i.close();
			System.out.println(s2.y + " " + s2.z);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
