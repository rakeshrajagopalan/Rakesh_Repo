package alarmpack;

import java.io.IOException;
import java.util.Calendar;

public class AutoShutdown implements Runnable {

	private static final int SHUTDOWN_HOUR = 02;

	private static final int SHUTDOWN_MINUTE = 30;

	private static final int TIMEOUT = 60000;

	public void run() {
		try {
			System.out.println("Shutdown Thread started");
			while (true) {
				Thread.sleep(TIMEOUT);
				Calendar calendar = Calendar.getInstance();
				if (calendar.get(Calendar.HOUR_OF_DAY) == SHUTDOWN_HOUR
						&& calendar.get(Calendar.MINUTE) == SHUTDOWN_MINUTE) {
					Runtime.getRuntime().exec("shutdown -s");
				}
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Thread autoShutdown = new Thread(new AutoShutdown());
		autoShutdown.start();
	}

}
