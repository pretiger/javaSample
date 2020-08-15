package ch01;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketExam {
	public static void main(String[] args) {
		ServerSocket socket=null;
		for(int i=0;i<65535;i++) {
			try {
				socket=new ServerSocket(i);
				socket.close();
			} catch (Exception e) {
				e.getStackTrace();
				System.out.println("port("+i+")을 사용중입니다.");
			}
		}
	}
}
