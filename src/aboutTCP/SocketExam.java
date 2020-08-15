package aboutTCP;

import java.net.Socket;

public class SocketExam {

	public static void main(String[] args) {
		Socket socket=null;
		for(int i=0; i <= 65535; i++) {
			try {
				socket=new Socket("localhost", i);
				socket.close();
			} catch (Exception e) {
				System.out.println(i+"번 포트는 사용중입니다.");
			}
		}
	}
}
