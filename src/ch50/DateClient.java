package ch50;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class DateClient {
	public static void main(String[] args) throws Exception {
		//서버에 접속 new Socket("서버IP",port번호)
		Socket s=new Socket("localhost",9100);
		//서버에서 보낸 메시지를 읽기 위한 스트림 생성
		BufferedReader input=new BufferedReader(new InputStreamReader(s.getInputStream()));
		//서버에서 보낸 메시지를 읽음
		String res=input.readLine();
		System.out.println(res);
		s.close();
		System.exit(0);
	}
}
