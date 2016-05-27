package multicasting;

import java.io.*;
import java.net.*;
import java.util.*;

public class MulticastServerThread extends QuoteServerThread {

	private long FIVE_SECONDS = 5000;

	public MulticastServerThread() throws IOException {
		super("MulticastServerThread");
	}

	public void run() {
		while (moreQuotes) {
			try {
				byte[] buf = new byte[256];

				// construct quote
				String dString = null;
				if (in == null)
					dString = new Date().toString();
				else
					dString = getNextQuote();
				buf = dString.getBytes();

				// send it
				InetAddress group = InetAddress.getByName("230.0.0.1");
				DatagramPacket packet = new DatagramPacket(buf, buf.length,
						group, 4446);
				socket.send(packet);

				// sleep for a while
				sleep(FIVE_SECONDS);
			} catch (Exception e) {
				e.printStackTrace();
				moreQuotes = false;
			}
		}
		socket.close();
	}
}
