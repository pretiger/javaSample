package aboutTCP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class DateClient {
	public static void main(String[] args) {
		Socket socket=null;
		try {
			socket=new Socket("localhost",9100);
			BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println(reader.readLine());
			socket.close();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
