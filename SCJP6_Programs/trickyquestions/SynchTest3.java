package trickyquestions;

public class SynchTest3 {
	private static synchronized void say() {
		System.out.println(Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		new Thread("LETTERS") {
			public void run() {
				synchronized (SynchTest3.class) {
					try {
						say();
						Thread.sleep(1000);
						say();
						Thread.sleep(1000);
						say();
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		}.start();
		new Thread("NUMBERS") {
			public void run() {
				synchronized (SynchTest3.class) {
					try {
						say();
						Thread.sleep(1000);
						say();
						Thread.sleep(1000);
						say();
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		}.start();
	}
}
