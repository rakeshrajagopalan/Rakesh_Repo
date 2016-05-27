package scjppack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SubtitleTrack_TimerModifier {

	private StringBuilder fileContent = new StringBuilder();

	private String indexer = "-->";

	private String filePath = "C:\\Users\\Rakesh\\Desktop\\Subtitles.txt";

	private int secondsDelayToAdd = 2;

	private int minuteDelayToAdd = 0;

	public SubtitleTrack_TimerModifier() throws FileNotFoundException,
			IOException {
		readFile();
		rewriteFile();
	}

	private void readFile() throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				filePath)));
		String data;
		while ((data = reader.readLine()) != null) {
			int indexPoint = data.indexOf(indexer);
			if (indexPoint != -1) {
				String startTime = data.substring(0, indexPoint).trim()
						.split(",")[0];
				String startMilliSec = data.substring(0, indexPoint).trim()
						.split(",")[1];
				String newStartTime = addDelay(secondsDelayToAdd,
						minuteDelayToAdd, startTime, startMilliSec) + " ";
				String endTime = data.substring(indexPoint + 3, data.length())
						.trim().split(",")[0];
				String endMillisec = data
						.substring(indexPoint + 3, data.length()).trim()
						.split(",")[1];
				String newEndTime = " "
						+ addDelay(secondsDelayToAdd, minuteDelayToAdd,
								endTime, endMillisec);
				data = newStartTime + indexer + newEndTime;
			}
			fileContent.append(data + "\n");
		}
		reader.close();
	}

	private void rewriteFile() throws IOException {
		PrintWriter writer = new PrintWriter(new FileWriter(new File(filePath)));
		writer.println(fileContent.toString());
		writer.close();
	}

	private String addDelay(int secondsDelayToAdd, int minuteDelayToAdd,
			String timeString, String milliSec) {
		String[] tokens = timeString.split(":");
		int startHour = Integer.valueOf(tokens[0]);
		int startMinute = Integer.valueOf(tokens[1]);
		int startSecond = Integer.valueOf(tokens[2]);
		if (secondsDelayToAdd != 0) {
			if ((startSecond += secondsDelayToAdd) >= 60) {
				startSecond = startSecond - 60;
				if ((startMinute += 1) >= 60) {
					startMinute = startMinute - 60;
					startHour += 1;
				}
			}
		}
		if (minuteDelayToAdd != 0) {
			if ((startMinute += minuteDelayToAdd) >= 60) {
				startMinute = startMinute - 60;
				startHour += 1;
			}
		}
		String returnString = "0" + startHour + ":";
		if (startMinute < 10) {
			returnString = returnString + "0" + startMinute + ":";
		} else {
			returnString = returnString + startMinute + ":";
		}
		if (startSecond < 10) {
			returnString = returnString + "0" + startSecond + ",";
		} else {
			returnString = returnString + startSecond + ",";
		}
		return returnString + milliSec;
	}

	public static void main(String[] args) {
		try {
			new SubtitleTrack_TimerModifier();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println("File rewritten");
	}

}
