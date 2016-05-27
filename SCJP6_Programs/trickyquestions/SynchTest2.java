package trickyquestions;

class MBThread extends Thread {
	String name;

	SynchTest2 st;

	public MBThread(String name, SynchTest2 st) {
		this.name = name;
		this.st = st;
	}

	public void run() {
		st.display(name);
	}
}

public class SynchTest2 {
	public static void main(String[] args) {
		MBThread first, second, third;
		SynchTest2 st2 = new SynchTest2();
		first = new MBThread("One", st2);
		second = new MBThread("Two", st2);
		third = new MBThread("Three", st2);
		second.start();
		first.start();
		third.start();
	}

	public void display(String name) {
		synchronized (name) {
			for (int i = 1; i <= 20; i++) {
				System.out.println("Name: " + name);
			}
		}
	}
}
