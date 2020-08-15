package ch50;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class GuguServer implements Runnable {
	private ServerSocket serverSocket;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public GuguServer() {
		try {
			serverSocket=new ServerSocket(9999);
			System.out.println("구구단 서비스가 시작되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		while(true) {
			try {
				socket=serverSocket.accept();
				InetAddress ip=socket.getInetAddress();
				System.out.println("클라이언트의 ip주소:"+ip);
				
				dis=new DataInputStream(socket.getInputStream());
				dos=new DataOutputStream(socket.getOutputStream());
				
				Thread th=new Thread(this);
				th.start();
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
				System.out.println("클라이언트에서 요청한 값:"+dan);
				StringBuilder sb=new StringBuilder();
				for(int i=1; i<=9; i++) {
					sb.append(dan+"X"+i+"="+dan*i+"\r\n");
				}
				dos.writeUTF(sb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
