package Client;

import java.net.Socket;
import java.util.ArrayList;

public class test extends C_main {

	private Socket socket = null;
	
	public test(String nickname, Socket socket) {
		super(nickname);
		this.socket = socket;
		say();
	}
	public void say() {
		System.out.println(socket + " " + super.nickname);

	}
	
	public static void main(String args[]) {
		ArrayList<String> wtf  = new ArrayList<>();
		System.out.println(wtf.size()); 
		wtf.add("DSFFDS");
		System.out.println(wtf.size());
	}
}
