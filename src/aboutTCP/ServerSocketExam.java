package aboutTCP;

import java.net.ServerSocket;

public class ServerSocketExam {

	public static void main(String[] args) {
		ServerSocket serverSocket=null;
		for(int i=0; i <= 65535; i++) {
			try {
				serverSocket=new ServerSocket(i);
				serverSocket.close();
			} catch (Exception e) {
				System.out.println(i+"번 포트는 사용중 입니다.");
			}
		}
	}
}
