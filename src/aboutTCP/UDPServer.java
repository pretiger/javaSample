package aboutTCP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class UDPServer {
	public static void main(String[] args) {
		byte[] data=new byte[65508];
		DatagramPacket dp=new DatagramPacket(data, data.length);
		try {
			DatagramSocket ds=new DatagramSocket(7777);
			System.out.println("UDPServer가 시작되었습니다.");
			ds.receive(dp);
			System.out.println("클라이언트 ip:"+dp.getAddress().getHostAddress());
			String receive=new String(dp.getData());
			System.out.println("클라이언트의 메시지:"+receive);
			
			System.out.println("응답할 메시지를 입력하세요.");
			Scanner scan=new Scanner(System.in);
			String msg=scan.nextLine();
			dp=new DatagramPacket(msg.getBytes(), msg.length(), dp.getAddress(), 8888);
			ds.send(dp);
			System.out.println("전송완료");
			
			scan.close();
			ds.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
