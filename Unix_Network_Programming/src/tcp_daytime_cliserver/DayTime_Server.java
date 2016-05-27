package tcp_daytime_cliserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DayTime_Server {

	public DayTime_Server() {
		try {
			acceptConnections();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void acceptConnections() {
		try {
			ServerSocket serverSocket = new ServerSocket(4242);
			System.out.println("Listening on port 4242");
			while(true) {
				Socket socket = serverSocket.accept();
				PrintWriter writer = new PrintWriter(socket.getOutputStream());
				writer.println("The Lord is my Shepherd");
				writer.flush();
				writer.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			new DayTime_Server();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
