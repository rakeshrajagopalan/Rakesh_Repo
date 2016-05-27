package chapter9;

public class DeadlockTest implements Runnable {

	@Override
	public void run() {
		try {
			synchronized (this) {
				System.out.println("Runnable is waiting");
				wait();
				for(int i =0;i<1000;i++) {
					System.out.println("Runnable " + i);
				}
				notify();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public DeadlockTest() {
		deadlockTest();
	}

	private void deadlockTest() {
		try {
			Thread t = new Thread(this);
			t.start();
			synchronized (this) {
				System.out.println("Thread is waiting");
				wait();
				for(int i =1001;i<2000;i++) {
					System.out.println("Thread " + i);
				}
				notify();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new DeadlockTest();
	}

}
