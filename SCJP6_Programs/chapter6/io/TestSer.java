package chapter6.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class class1 implements Serializable {
	public transient int i = 9;
}

public class TestSer {
	public static void main(String[] args) throws Exception {
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("d:\\1.ser"));
		o.writeObject(new class1());
		o.close();
		ObjectInputStream i = new ObjectInputStream(new FileInputStream("d:\\1.ser"));
		class1 one = (class1) i.readObject();
		i.close();
		System.out.println(one.i);
	}
}
