package pipepack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class PipeServer2 {
	
	private WritableByteChannel writeChannel;

	private ReadableByteChannel readChannel;

	private StringBuilder pathData = new StringBuilder();

	public void readDataFromPipe(Pipe pipe) {
		try {
			// Step 5: The server reads the file path, then stores the file path
			// in a global variable.
			readChannel = pipe.source();
			ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
			while (readChannel.read(byteBuffer) >= 0) {
				byteBuffer.flip();
				pathData.append(new String(byteBuffer.array()).trim());
			}
			readChannel.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void writeDataToPipe(Pipe pipe) {
		try {
			// Step 8: The server attempts to open the file the client wants.
			// Then, it writes the contents of the file in the WriteChannel.
			writeChannel = pipe.sink();
//			BufferedReader reader = new BufferedReader(new FileReader(pathData
//					.toString()));
//			StringBuilder fileData = new StringBuilder();
//			String data;
//			while ((data = reader.readLine()) != null) {
//				fileData.append(data + "\n");
//			}
			Process process = Runtime.getRuntime().exec("ls -lh | grep 'Rakesh'");
			ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
			byteBuffer.putInt(new InputStreamReader(process.getInputStream()).read());
			byteBuffer.flip();
			writeChannel.write(byteBuffer);
			writeChannel.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
