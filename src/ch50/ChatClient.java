package ch50;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	public static void main(String[] args) throws Exception {
		Socket socket=null;
		//데이터 송수신을 위한 스트림
		PrintWriter writer=null;
		BufferedReader reader=null;
		try {
			//서버에 접속 new Socket("서버ip",포트)
			socket=new Socket("localhost", 9999);
			//데이터 송수신을 위한 스트림 생성
			writer=new PrintWriter(socket.getOutputStream(), true);
			reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (Exception e) {
			System.out.println("서버에 접속할 수 없습니다.");
			e.printStackTrace();
		}
		
		String receive="";
		String send;
		Scanner sc=new Scanner(System.in);
		while(true) {
			//서버에서 보낸 메시지를 받음
			receive=reader.readLine();
			System.out.println("[서버]"+receive);
			if(receive.equals("quit")) break;//종료조건
			System.out.println("입력하세요(종료:quit):");
			send=sc.nextLine();//키보드 입력
			if(send.equals("quit")) break;//종료조건
			//클라이언트에 메시지 전달
			if(send!=null) writer.println(send);
		}
		//리소스 정리
		sc.close();
		writer.close();
		reader.close();
		socket.close();
	}
}
