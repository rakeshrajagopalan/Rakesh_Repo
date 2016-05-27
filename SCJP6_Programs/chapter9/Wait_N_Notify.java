package chapter9;

public class Wait_N_Notify implements Runnable {

	public Wait_N_Notify() {
		synchTest();
	}

	private void synchTest() {
		try {
			Thread thread = new Thread(this);
			thread.setName("Rakesh");
			thread.start();
			System.out.println("Thread '" + Thread.currentThread().getName()
					+ "' is going to sleep");
			Thread.sleep(5000);
			System.out.println("Thread '" + Thread.currentThread().getName()
					+ "' has got up");
			synchronized (this) {
				for (int i = 1; i <= 500; i++) {
					System.out.print(i + " ");
				}
				System.out.println("\nThread '"
						+ Thread.currentThread().getName()
						+ "' is going to notify");
				notify();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void run() {
		try {
			synchronized (this) {
				System.out.println("Thread '"
						+ Thread.currentThread().getName() + "' is waiting");
				wait();
				System.out.println("Thread '"
						+ Thread.currentThread().getName() + "'s wait is over");
				for (int i = 501; i <= 1000; i++) {
					System.out.print(i + " ");
				}
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Wait_N_Notify();
	}

}
