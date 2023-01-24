package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


public class C_main extends Thread {
	
	/*
	 * set your nickname! 
	 */
	protected String nickname = null;
	private boolean initiate = true;
	/*
	 * server entrance 
	 */
	protected Socket socket = null;
	
	private ReadThread readThread = null;
	private WriteThread writeThread = null;
	
	public C_main(String nickname) {
		this.nickname = nickname; 	// set up your own nickname!	
	}
	
	
	public void run() {
		
		while(initiate) {
			
			try {
				//129.186.194.48
				//10.50.48.49
				//127.0.0.1
				//192.168.0.105
	          socket = new Socket("127.0.0.1", 8080);
				System.out.println("Pusedo-Connected");
	          // what if readThread is not null???
	         				
	          readThread = new ReadThread(nickname, socket);
	          readThread.start(); // ListeningThread Start
				
	          // To check if this is reconnected or connected for the first time.
				if(writeThread==null) {
					writeThread = new WriteThread(nickname, socket);
					writeThread.start();   				 // WritingThread Start
				}
				else {
					// if this is a reconnection, send this client's data to the server to update it.
					writeThread.setSocket(socket);
				}
				System.out.println("Connected");
				initiate = false;			// socket initiated. stop inititating.
	      }
			catch (SocketException s) {
				System.out.println("Access Denied");
//				return ;
			}
		
	      catch (UnknownHostException u) {
	          System.out.println("Failed to Connect: " + u);
	      }
	      catch (IOException i) {
	          System.out.println("Failed to Connect: " + i);
	      }
		}
	}
	
	public static void main(String args[]) {
		
		String nickname = "Joon";
		
		C_main user = new C_main(nickname);
		user.start();
		
	}
}









