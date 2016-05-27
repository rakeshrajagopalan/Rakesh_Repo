package chapter9;

public class SynchExample implements Runnable {

	private StringBuffer buffer;
	
	private SynchExample synchExample;

	public SynchExample(StringBuffer buffer) {
		try {
			synchExample = this;
			this.buffer = buffer;
			Thread aThread = new Thread(this, "A");
			Thread bThread = new Thread(this, "B");
			Thread cThread = new Thread(this, "C");
			synchronized (this) {
				aThread.start();
				bThread.start();
				cThread.start();
			}
			Thread.sleep(1000);
			char[] chars = buffer.toString().toCharArray();
			for (char c : chars) {
				System.out.print(c + " ");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void run() {
		try {
			synchronized (synchExample) {
				if (Thread.currentThread().getName().equals("A")) {
					for (int i = 0; i < 100; i++) {
						buffer.append("A");
					}
				} else if (Thread.currentThread().getName().equals("B")) {
					for (int i = 0; i < 100; i++) {
						buffer.append("B");
					}
				} else if (Thread.currentThread().getName().equals("C")) {
					for (int i = 0; i < 100; i++) {
						buffer.append("C");
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new SynchExample(new StringBuffer());
	}

}
