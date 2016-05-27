package tcp_daytime_cliserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	private Socket socket;

	public void acceptRequests(String portNum) {
		try {
			ServerSocket serverSock = new ServerSocket(Integer
					.parseInt(portNum));
			while (true) {
				socket = serverSock.accept();
				Thread thread = new Thread(new Runnable() {
					public void run() {
						processRequests();
					}
				});
				thread.setDaemon(true);
				thread.start();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void processRequests() {
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.println("Welcome");
			writer.flush();
			writer.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			if (reader.readLine() != null) {
				writer = new PrintWriter(socket.getOutputStream());
				writer.println(System.currentTimeMillis());
				writer.flush();
				writer.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new TCPServer().acceptRequests("7575");
	}
}
