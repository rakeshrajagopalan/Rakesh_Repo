package tcp_daytime_cliserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DayTime_Client {

	public DayTime_Client() {
		try {
			talkToServer();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void talkToServer() {
		try {
			Socket socket = new Socket("127.0.0.1", 7575);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			System.out.println("Message from server: " + reader.readLine());
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			new DayTime_Client();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
