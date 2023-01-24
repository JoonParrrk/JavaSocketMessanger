package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;



public class ReadThread extends C_main{

	private Socket socket = null;
	private InputStreamReader inputStream = null;
	private BufferedReader bufferedReader = null;
	
	
	public ReadThread(String nickname, Socket socket) {
		super(nickname);
		this.socket = socket;
	}
	

	public void run() {
		
		try {
			System.out.println(socket.toString() + " This socket");
			inputStream = new InputStreamReader(socket.getInputStream());
			bufferedReader = new BufferedReader(inputStream);
			
			String incomingMessage = null;
			
			while (!socket.isClosed()) {

				incomingMessage = bufferedReader.readLine();
					
					if(incomingMessage != null) {
						System.out.println("Server: " + incomingMessage);
//						incomingMessage = null;
					}
			} 
			
		} catch (IOException e) {
			System.out.println("ERROR");

			e.printStackTrace();
		}
		
		
	}

	

}
