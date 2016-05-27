package alarmpack;

import java.io.IOException;
import java.util.Calendar;

public class Vishnu_Sahasranamam_Alarm implements Runnable {

	private static final int ALARM_TIMEOUT_HOUR = 10;

	private static final int ALARM_TIMEOUT_MINUTE = 55;

	private static final long TIMEOUT = 60000;

	private boolean timeOut = false;

	private static final String[] COMMANDS = {
			"C:\\Program Files\\Windows Media Player\\wmplayer.exe",
			"E:\\Devotional Songs\\03 Vishnu Sahasranamam.wma" };

	public Vishnu_Sahasranamam_Alarm() {
		Thread alarmRaiser = new Thread(this);
		alarmRaiser.start();
	}

	public void run() {
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
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(COMMANDS);
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

	public static void main(String[] args) {
		new Vishnu_Sahasranamam_Alarm();
	}

}
