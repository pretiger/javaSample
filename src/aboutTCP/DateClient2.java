package aboutTCP;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class DateClient2 {
	public static void main(String[] args) {
		Socket clientS=null;
		DataInputStream dis=null;
		
		try {
			clientS=new Socket("localhost", 9100);
			dis=new DataInputStream(clientS.getInputStream());
			System.out.println(dis.readUTF());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}
