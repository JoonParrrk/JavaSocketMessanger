package Server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThreadServer extends S_main {
	
	private Socket socket = null;
	private InputStreamReader inputStream = null;
	private BufferedReader bufferedReader = null; 
	
	public ReadThreadServer(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {

		String message = null;
		
		try {
		
			inputStream = new InputStreamReader(socket.getInputStream());
			bufferedReader = new BufferedReader(inputStream);
			
			while (true) {
				message = bufferedReader.readLine();
				
				if(message != null) {
					System.out.println("Client: " + message);
					message = null;
				}
			}
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}











  



