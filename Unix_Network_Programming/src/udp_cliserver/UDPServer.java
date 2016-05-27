package udp_cliserver;

import java.net.*;

class UDPServer {

	public static void main(String args[]) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(9876);
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		System.out.println("Server listening at port: "
				+ serverSocket.getLocalPort());
		while (true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData,
					receiveData.length);
			serverSocket.receive(receivePacket);
			String sentence = new String(receivePacket.getData());
			System.out.println("Message from Client: " + sentence.trim());
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			sendData = receiveData;
			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
		}
	}

}
