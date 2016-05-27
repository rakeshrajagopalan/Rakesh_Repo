package trickyquestions;

import java.util.ArrayList;
import java.util.List;

public class Queue {

	private List<String> list = new ArrayList<String>();

	public void put(String o) {
		synchronized (this) {
			list.add(o);
			notify();
		}
	}

	public String get() {
		String s = null;
		try {
			synchronized (this) {
				wait();
				s = list.get(list.size() - 1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return s;
	}

	public static void main(String[] args) {
		final Queue queue = new Queue();
		Thread putter = new Thread(new Runnable() {
			public void run() {
				while (true) {
					queue.put("1");
					try {
						Thread.sleep(5000L);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		putter.start();
		for (int i = 0; i <= 5; i++) {
			Thread getter = new Thread(new Runnable() {
				public void run() {
					System.out.println(queue.get());
					try {
						Thread.sleep(1000L);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			getter.start();
		}
	}
}
