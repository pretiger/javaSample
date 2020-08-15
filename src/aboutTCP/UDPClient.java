package aboutTCP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
	public static void main(String[] args) {
		try {
			InetAddress address=InetAddress.getByName("localhost");
			System.out.println("전송할 내용을 입력하세요.");
			Scanner scan=new Scanner(System.in);
			String msg=scan.nextLine();
			byte[] send=msg.getBytes();
			DatagramPacket dp=new DatagramPacket(send, send.length, address, 7777);
			DatagramSocket ds=new DatagramSocket();
			ds.send(dp);
			System.out.println("전송완료");
			ds.close();
			
			ds=new DatagramSocket(8888);
			byte[] data=new byte[65508];
			dp=new DatagramPacket(data, data.length);
			ds.receive(dp);
			msg=new String(dp.getData());
			System.out.println("전송받은 내용:"+msg);
			
			scan.close();
			ds.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
