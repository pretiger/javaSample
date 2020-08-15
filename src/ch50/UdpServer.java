package ch50;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class UdpServer {
	public static void main(String[] args) {
		byte[] data=new byte[65508];//바이트 배열
		DatagramPacket packet=new DatagramPacket(data, data.length);
		try {
			DatagramSocket socket=new DatagramSocket(7777);//7777포트 개방
			System.out.println("서비스가 시작되었습니다.");
			socket.receive(packet);//클라이언트에서 보낸 패킷을 받음
			System.out.println("클라이언트의 ip주소:"+packet.getAddress().getHostAddress());
			//패킷에 저장된 바이트 배열을 스트링으로 변환
			String receive=new String(packet.getData());
			System.out.println("클라이언트의 메시지:"+receive);
			
			//메시지를 보내기 위한 코드
			System.out.println("응답할 메시지를 입력하세요:");
			Scanner scan=new Scanner(System.in);
			String msg=scan.nextLine();
			//new DatagramPacket(바이트배열, 사이즈, 서버ip, 서버port)
			packet=new DatagramPacket(msg.getBytes(), msg.getBytes().length, packet.getAddress(), 8888);
			socket.send(packet);
			System.out.println("전송이 완료되었습니다.");
			socket.close();//소켓 닫기
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
