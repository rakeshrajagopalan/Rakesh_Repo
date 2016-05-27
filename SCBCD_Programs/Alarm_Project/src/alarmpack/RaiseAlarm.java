package alarmpack;

import java.io.IOException;
import java.util.Calendar;

public class RaiseAlarm implements Runnable {

	private static final int ALARM_TIMEOUT_HOUR = 00;

	private static final int ALARM_TIMEOUT_MINUTE = 42;

	private static final long TIMEOUT = 2000;

	private boolean timeOut = false;

	private static final String[] COMMANDS = {
			"C:\\Program Files\\Windows Media Player\\wmplayer.exe",
			"C:\\Users\\Rakesh\\Desktop\\Temp\\Windows Default.wav" };

	public RaiseAlarm() {
		Thread alarmRaiser = new Thread(this);
		alarmRaiser.start();
	}

	@Override
	public void run() {
		System.out.println("Thread started");
		while (!timeOut) {
			checkTimeOut();
		}
	}

	private void checkTimeOut() {
		try {
			Thread.sleep(TIMEOUT);
			Calendar currentTime = Calendar.getInstance();
			if ((currentTime.get(Calendar.HOUR_OF_DAY) == ALARM_TIMEOUT_HOUR)
					&& (currentTime.get(Calendar.MINUTE) == ALARM_TIMEOUT_MINUTE)) {
				timeOut = true;
				startSong();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	private void startSong() {
		try {
			Process process = Runtime.getRuntime().exec(COMMANDS);
			try {
				process.waitFor();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			process.destroy();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	void test() {
		System.out.println("Hello World.. Accessing through Reflection");
	}

	public static void main(String[] args) {
		new RaiseAlarm();
	}

}
