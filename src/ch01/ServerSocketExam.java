package ch01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerSocketExam {
	public static void main(String[] args) {
		try {
			ServerSocket ss=new ServerSocket(9100);
			System.out.println("서비스가 시작되었습니다.");
			while(true) {
				Socket s= ss.accept();
				PrintWriter pw=new PrintWriter(s.getOutputStream(),true);
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
				String str=sdf.format(new Date());
				pw.println(str);
				s.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
