package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


public class S_main extends Thread {

	/*
	 * total sockets included 
	 */
	protected ArrayList<Socket> socketList = null;
	/*
	 * later when the socket sends its username, it will be loaded to the userData. ex) {socket 168.127.0.1 = "John"}
	 */
	protected HashMap<Socket, String> userData = null;
	
	protected static S_main server = null;
	
	private String message = null;
	
	public S_main() {
		socketList=new ArrayList<Socket>();
		userData = new HashMap<>();
	}
	
	public void run() {
		 
		    	try {
			         ServerSocket ssk = new ServerSocket(8080); // setting up the port 
			         System.out.println("Server opened");
			         
			         WriteThreadServer writeThreadServer = new WriteThreadServer();
			         
			         writeThreadServer.start();
			         
			         while(true) {
			        	 
			    	  Socket socketUser = ssk.accept();

			    	  socketList.add(socketUser);							    	  //added to the server
		    	  
			    	  System.out.println(socketList);
			    	  System.out.println("User Accepted");
			    	  
			    	  ReadThreadServer readThreadServer = new ReadThreadServer(socketUser);  // later update rTS within an arrayList so that you can close it when there is no user.
			    	  readThreadServer.start();  
			   	    	  
			         }
		    	}
		    	catch(IOException e) {
		    		e.printStackTrace();
		    	}  
		    
	}
	
	
	public static void main(String[] args) {
		server = new S_main();
		server.start();
	}

}
