package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class WriteThread extends C_main {

	private Socket socket = null;
	private OutputStreamWriter outputStreamWriter = null;
	private BufferedReader bufferedReader = null;
	private BufferedWriter bufferedWriter = null;

	private String messageToSend = null;
	
	
	public WriteThread(String nickname, Socket socket) throws IOException {
		super(nickname);
		this.socket = socket;
		setWriter();
	}


	public void setSocket(Socket socket) throws IOException {
		this.socket=socket;
		outputStreamWriter.close();
//		out.close();
		setWriter();
	}
	public void setWriter() throws IOException {
		outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
		bufferedWriter  = new BufferedWriter(outputStreamWriter);
//		writer = new PrintWriter(out, true);
//		out.write(("#"+ name).getBytes());
	}		
	public void run() {
		try {
			
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));

			while(!socket.isClosed()) {
				
				// for scanning what you type
				
				messageToSend = bufferedReader.readLine();
				bufferedWriter.write(messageToSend);
				bufferedWriter.newLine();
				bufferedWriter.flush();  
			}
		}
		catch(Exception e) {
			System.out.println("SOMETHING WENT WRONG...");
			e.printStackTrace();
		}
	}
}








