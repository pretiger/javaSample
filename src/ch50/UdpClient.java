package ch50;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {
	public static void main(String[] args) {
		try {
			//서버의 ip주소 정보
			InetAddress address=InetAddress.getByName("localhost");
			System.out.print("서버에 보낼 메시지를 입력하세요:");
			Scanner scan=new Scanner(System.in);
			String data=scan.nextLine();
			byte[] send=data.getBytes();//스트링을 바이트 배열로 변환
			//패킷생성 new DatagramPacket(바이트배열, 사이즈, 서버ip, 포트번호)
			DatagramPacket packet=new DatagramPacket(send, send.length, address, 7777);
			//데이터 전달을 위한 소켓 생성
			DatagramSocket socket=new DatagramSocket();
			socket.send(packet);//서버에 데이터 전송
			socket.close();//소켓 닫기
			System.out.println("전송되었습니다.");
			
			//메시지를 수신하기 위한 코드, 새로운 포트를 개방해야함
			DatagramSocket socket2=new DatagramSocket(8888);
			byte[] data2=new byte[65508];//바이트 배열
			//udp 통신에서는 데이터 전송을 바이트 배열로 처리
			DatagramPacket packet2=new DatagramPacket(data2, data2.length);
			socket2.receive(packet2);//메시지 수신
			String message=new String(packet2.getData());//바이트 배열을 스트링으로 
			System.out.println("수신한 메시지:"+message);
			socket2.close();//소켓 닫기
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
