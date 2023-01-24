package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class WriteThreadServer extends S_main {
	
	
//	private ArrayList<Socket> socketList = null;
	/*
	 * message has to be coming from the Server GUI. Meaning there has to be a method created in Server class.
	 */
	private OutputStreamWriter outputStreamWriter = null;
	private BufferedReader bufferedReader = null;
	private BufferedWriter bufferedWriter = null;

	private String message = null;


	public WriteThreadServer() { // IOException (When one socket leaves unexpectedly) Server must diagnose which socket did and what caused the error.
		
	}
	public void run() {
		
		
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(System.in));
				
				while (true) {
				message = bufferedReader.readLine();
				
					if(!(message == null && message == "\n")) {
						String temp = "admin: " + message ;		
//						System.out.println(server.socketList.size() + " FROM WTS");
						speak(temp);
//						message =null;
					}
				}
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
	}
	public void speak(String msg) {
			
		for(int i=0;i< server.socketList.size();i++) {
			
//			if(socketList.get(i).isClosed()) {
//				socketList.remove(i);
//			}		 	
			
			try {
				outputStreamWriter = new OutputStreamWriter(server.socketList.get(i).getOutputStream());
				bufferedWriter  = new BufferedWriter(outputStreamWriter);
				bufferedWriter.write(message);
				bufferedWriter.newLine();
				bufferedWriter.flush();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}














