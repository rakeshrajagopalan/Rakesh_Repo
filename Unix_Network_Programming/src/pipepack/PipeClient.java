package pipepack;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class PipeClient {

	private WritableByteChannel writeChannel;

	private ReadableByteChannel readChannel;
	
	public void readDataFromPipe(Pipe pipe) {
		try {
			// Step 10: A readable byte channel is opened, and the contents are
			// read.
			readChannel = pipe.source();
			ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
			StringBuilder fileContents = new StringBuilder();
			while (readChannel.read(byteBuffer) >= 0) {
				byteBuffer.flip();
				fileContents.append(new String(byteBuffer.array()));
			}
			readChannel.close();
			System.out.println("The file contents are: "
					+ fileContents.toString().trim());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void writeDataToPipe(Pipe pipe) {
		try {
			// Step 3: Gets the Write Channel from the pipe, creates a byte
			// buffer and writes the path of the file it needs in the Write
			// Channel.
			writeChannel = pipe.sink();
			ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
			byteBuffer.put("d:\\Movies\\Truth.txt".getBytes());
			byteBuffer.flip();
			writeChannel.write(byteBuffer);
			writeChannel.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
