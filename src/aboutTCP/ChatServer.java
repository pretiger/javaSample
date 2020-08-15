package aboutTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
	public static void main(String[] args) {
		ServerSocket serverS=null;
		Socket clientS=null;
		PrintWriter pw=null;
		BufferedReader br=null;
		try {
			serverS=new ServerSocket(9100);
			System.out.println("ChatServer를 시작합니다.");
			clientS=serverS.accept();
			System.out.println("클라이언트 ip:"+clientS.getInetAddress().getHostAddress());
			pw=new PrintWriter(clientS.getOutputStream(),true);
			br=new BufferedReader(new InputStreamReader(clientS.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String send="ChatServer에 접속한 것을 환영합니다.";
		String receive="";
		pw.println(send);
		Scanner scn=new Scanner(System.in);
		while(true) {
			try {
				receive=br.readLine();
				System.out.println("[클라이언트]"+receive);
				if(receive==null || receive.equals("quit")) break;
				System.out.print("내용을 입력하세요(EXIT:quit):");
				send=scn.nextLine();
				pw.println(send);
				if(send.equals("quit")) break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		scn.close();
		pw.close();
		try {
			if(br!=null) br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if(clientS!=null) clientS.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if(serverS!=null) serverS.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}
