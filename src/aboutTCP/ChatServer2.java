package aboutTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
//1:1 대화이며 한명이 입력할때까지 받는 사람은 대기 상태이다.즉, 대화는 주고 받기만 가능하다 한사람이 일방적으로 입력은 불가능
public class ChatServer2 {
	public static void main(String[] args) {
		ServerSocket serverS=null;
		Socket clientS=null;
		DataInputStream dis=null;
		DataOutputStream dos=null;
		
		try {
			serverS=new ServerSocket(9100);
			System.out.println("ChatServer2 시작합니다.");
			clientS=serverS.accept();
			System.out.println("클라이언트 ip:"+clientS.getInetAddress().getHostAddress());
			dis=new DataInputStream(clientS.getInputStream());
			dos=new DataOutputStream(clientS.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String receive="";
		String send="ChatServer2에 접속하신걸 환영합니다.";
		Scanner scan=new Scanner(System.in);
		try {
			dos.writeUTF(send);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while(true) {
			try {
				receive=dis.readUTF();
				System.out.println("[클라이언트]"+receive);
				if(receive==null || receive.equals("quit")) break;
				System.out.print("내용을 입력하세요(EXIT:quit):");
				send=scan.nextLine();
				dos.writeUTF(send);  //전송후 while문을 빠져야함.
				if(send.equals("quit")) break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		scan.close();
		try {
			if(dis!=null) dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if(dos!=null) dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if(clientS!=null) clientS.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if(serverS!=null) serverS.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
