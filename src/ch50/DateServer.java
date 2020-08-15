package ch50;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateServer {
	public static void main(String[] args) throws IOException {
		ServerSocket server=new ServerSocket(9100);//서버소켓 생성
		System.out.println("서비스가 시작되었습니다.");
		while(true) {
			Socket socket=server.accept();//클라이언트의 접속을 기다림
			//접속이 이루어지면 스트림 생성
			PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
			//날짜(숫자형식)를 스트링 형식으로 변환
			String str=sdf.format(new Date());//java.util.Date
			out.println(str);//클라이언트에 메시지를 보냄
			socket.close();
		}
	}
}
