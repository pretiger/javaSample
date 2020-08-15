package aboutTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GuguServer implements Runnable {
	ServerSocket ss=null;
	Socket socket=null;
	DataInputStream dis=null;
	DataOutputStream dos=null;

	public GuguServer() {
		try {
			ss=new ServerSocket(9100);
			System.out.println("GuguServer 시작");
		} catch (Exception e) {
			e.printStackTrace();
		}
		while(true) {
			try {
				socket=ss.accept();
				System.out.println("클라인언트 ip:"+socket.getInetAddress());
				dis=new DataInputStream(socket.getInputStream());
				dos=new DataOutputStream(socket.getOutputStream());
				Thread th=new Thread(this);
				th.start();
//				스레드를 사용하여 run으로 처리하는 이유:GuguClient에서 application을 종료하면 run() 쪽에서 dis.readInt()==null
//				이 되어 Exception에 걸리면서 run()이 종료된다. main에 run()부분의 내용을 두게 되면 계속 while문에 의해 error message가
//				반복됨(GuguServer2.java 를 실행하여 확인가능)
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new GuguServer();
	}

	@Override
	public void run() {
		try {
			while(true) {
				int dan=dis.readInt();
				System.out.println("클라이어트에서 요청한 값 : "+dan);
				StringBuilder sb=new StringBuilder();
				for(int i=1;i<=9;i++) {
					sb.append(dan+"X"+i+"="+dan*i+"\r\n");
				}
				dos.writeUTF(sb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
