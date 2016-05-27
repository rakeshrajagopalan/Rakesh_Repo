package chapter7;

import java.io.*;

public class Counter implements Serializable {

	private int counter;

	private String fileName = "f:\\Counter.txt";

	public int counter() {
		counter += 1;
		return counter;
	}

	private Counter getCounter() {
		return this;
	}

	public void serialize() {
		try {
			FileOutputStream fOut = new FileOutputStream(fileName);
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(getCounter());
			oOut.close();
			fOut.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void deserialize() {
		try {
			File file = new File(fileName);
			if (file.exists()) {
				FileInputStream fIn = new FileInputStream(file);
				ObjectInputStream oIn = new ObjectInputStream(fIn);
				Counter desObj = (Counter) oIn.readObject();
				int temp = desObj.counter;
				System.out.println("The deserialized value is: " + temp);
				if (desObj != null && temp != 0) {
					counter = temp;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
