package ch01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class GuguServer implements Runnable {
	ServerSocket ss;
	Socket s;
	DataInputStream dis;
	DataOutputStream dos;
	
	public GuguServer() {
		try {
			ss=new ServerSocket(9100);
			System.out.println("Gugu서비스 시작");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true) {
			try {
				s=ss.accept();
				InetAddress ip=s.getInetAddress();
				System.out.println("Client IP : "+ip);
				dis=new DataInputStream(s.getInputStream());
				dos=new DataOutputStream(s.getOutputStream());
				Thread th=new Thread(this);
				th.start();
			} catch (IOException e) {
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
				StringBuilder sb=new StringBuilder();
				for(int i=1;i<=9;i++) {
					sb.append(dan+"X"+i+"="+dan*i+"\r\n");
				}
				dos.writeUTF(sb.toString());
				s.close();
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
}
