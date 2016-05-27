package trickyquestions;

/**
 * Letters A and B to be printed alternatively forever.
 * 
 * @author Rakesh
 * 
 */

public class ThreadCommunication implements Runnable {

	public ThreadCommunication() {
		try {
			Thread thread = new Thread(this);
			thread.start();
			Thread.sleep(1000);
			synchronized (this) {
				while (true) {
					Thread.sleep(100);
					System.out.print("A");
					notify();
					wait();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void run() {
		try {
			synchronized (this) {
				while (true) {
					wait();
					Thread.sleep(100);
					System.out.print("B");
					notify();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ThreadCommunication();
	}

}
