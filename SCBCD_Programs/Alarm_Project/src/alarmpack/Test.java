package alarmpack;

import java.io.IOException;

public class Test {
	public static void main(String[] args) {
		try {
			Runtime.getRuntime().exec("Control Panel\\Network Connections\\DataOne.exe");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
