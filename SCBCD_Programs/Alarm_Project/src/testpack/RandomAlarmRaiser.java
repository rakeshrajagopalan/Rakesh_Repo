package testpack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RandomAlarmRaiser implements Runnable {

	private static final int ALARM_TIMEOUT_HOUR = 23;

	private static final int ALARM_TIMEOUT_MINUTE = 00;

	private static final long TIMEOUT = 60000;

	private boolean timeOut = false;

	private static final String[] SONGLIST;

	private List<File> fileNameList = new ArrayList<File>();

	private List<File> dirNameList = new ArrayList<File>();
	
	static {
		SONGLIST = new String[13];
		SONGLIST[0] = "E:\\Devotional Songs\\01 Devotional songs - T.M.S\\SOLLATHANAAL.mp3";
		SONGLIST[1] = "E:\\Devotional Songs\\01 Devotional songs - T.M.S\\Alagendra Sollukku.mp3";
		SONGLIST[2] = "E:\\Devotional Songs\\Krishna Ghanam\\Aayar_Padi_Maligayil.mp3";
		SONGLIST[3] = "E:\\Devotional Songs\\Krishna Ghanam\\Pulankuzhal_Kodutha.mp3";
		SONGLIST[4] = "E:\\Devotional Songs\\05 Hanuman_Chalisa.mp3";
		SONGLIST[5] = "E:\\Devotional Songs\\GANESHA_PANCHARATNAM.wma";
		SONGLIST[6] = "E:\\Devotional Songs\\KANNAPURAM SELVAN.mp3";
		SONGLIST[7] = "E:\\Devotional Songs\\GURUVAYURAPPA.mp3";
		SONGLIST[8] = "E:\\Devotional Songs\\kurai_ondrum.wma";
		SONGLIST[9] = "E:\\Devotional Songs\\NAMA RAMAYANA.wma";
		SONGLIST[10] = "E:\\Devotional Songs\\NARAYANA ENUM.mp3";
		SONGLIST[11] = "E:\\Devotional Songs\\THIRUPATHI MAZHAI.mp3";
		SONGLIST[12] = "E:\\Devotional Songs\\Vinayagane_Vinay_Theerpavane.mp3";
	}

	public RandomAlarmRaiser() {
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

	public void listFilesInDir(String path) {
		File file = new File(path);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File nextFile : files) {
				if (nextFile.isDirectory()) {
					dirNameList.add(nextFile);
				} else {
					if (!nextFile.getName().startsWith(
							"Iraivanidam Kaiyendhungal")) {
						fileNameList.add(nextFile);
					}
				}
			}
			if (dirNameList.size() > 0) {
				File nextFile = dirNameList.get(0);
				dirNameList.remove(0);
				listFilesInDir(nextFile.getPath());
			}
		} else {
			fileNameList.add(file);
		}
	}

	private void startSong() {
		try {
			int number = ((int)(Math.random() * 12));
			String[] commands = {
					"C:\\Program Files\\Windows Media Player\\wmplayer.exe",
					SONGLIST[number] };
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(commands);
			try {
				process.waitFor();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			process.destroy();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new RandomAlarmRaiser();
	}

}
