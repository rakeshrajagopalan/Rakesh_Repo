package pipepack;

import java.nio.channels.Pipe;

public class PipeMain {

	public void facilitateCommunication() {
		try {
			// Step 1: Open the pipe
			Pipe pipe1 = Pipe.open();
			PipeClient pipeClient = new PipeClient();
			PipeServer2 pipeServer = new PipeServer2();
			// Step 2: Pass the pipe to Client, which will write the path of the
			// file in the server it is interested in.
			pipeClient.writeDataToPipe(pipe1);
			// Step 4: It then passes the pipe to the Server, which will open
			// the read channel and will read the path of the file.
			pipeServer.readDataFromPipe(pipe1);
			// Step 6: Now, a new Pipe is created.
			Pipe pipe2 = Pipe.open();
			// Step 7: The file contents will be read by the server and written
			// into the Write Channel.
			pipeServer.writeDataToPipe(pipe2);
			// Step 9: The pipe, with the file contents in its sink, is passed
			// on to the client.
			pipeClient.readDataFromPipe(pipe2);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new PipeMain().facilitateCommunication();
	}

}
