package ch01;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpServer {
	public static void main(String[] args) {
		byte[] data=new byte[65508];
		DatagramPacket dp=new DatagramPacket(data, data.length);
		try {
			DatagramSocket ds=new DatagramSocket(7777);
			System.out.println("UdpServer가 시작 되었습니다.");
			ds.receive(dp);
			System.out.println("Client ip-Address : "+dp.getAddress().getHostAddress());
			String receive=new String(dp.getData());
			System.out.println("Client Message : "+receive);
			ds.close();
			
			InetAddress ip=InetAddress.getByName("localhost");
			System.out.println("서버에 보낼 메시지를 입력하세요.");
			Scanner s=new Scanner(System.in);
			String str=s.nextLine();
			byte[] send=str.getBytes();
			DatagramPacket dp2=new DatagramPacket(send, send.length, ip, 8888);
			DatagramSocket ds2=new DatagramSocket();
			ds2.send(dp2);
			System.out.println("전송 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
