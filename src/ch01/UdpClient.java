package ch01;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {
	public static void main(String[] args) {
		try {
			InetAddress ia=InetAddress.getByName("localhost");
			System.out.println("서버에 보낼 메시지를 입력하세요.");
			Scanner s=new Scanner(System.in);
			String data=s.nextLine();
			byte[] send=data.getBytes();
			DatagramPacket dp=new DatagramPacket(send, send.length, ia, 7777);
			DatagramSocket ds=new DatagramSocket();
			ds.send(dp);
			ds.close();
			System.out.println("전송되었습니다.");
			
			byte[] data2=new byte[65508];
			DatagramPacket dp2=new DatagramPacket(data2, data2.length);
			DatagramSocket ds2=new DatagramSocket(8888);
			System.out.println("UdpServer 8888 가 시작 되었습니다.");
			ds2.receive(dp2);
			System.out.println("Client ip-Address : "+dp2.getAddress().getHostAddress());
			String receive=new String(dp2.getData());
			System.out.println("Client Message : "+receive);
			ds2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
