package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class ChatServer {
	
	public static void main(String[] args) {
		ServerSocket ss=null;
		Socket s=null;
		PrintWriter pw=null;
		BufferedReader br=null;
		Scanner sc=null;
		try {
			ss=new ServerSocket(9100);
			System.out.println("ChatServer Service Start");
			s=ss.accept();
			InetAddress ip=s.getInetAddress();
			System.out.println("Client IP : "+ip);
			pw=new PrintWriter(s.getOutputStream(),true);
			br=new BufferedReader(new InputStreamReader(s.getInputStream()));
			String receive="";
			String send="Welcom Server";
			pw.println(send);
			sc=new Scanner(System.in);
			while(true) {
				receive=br.readLine();
				if(receive==null || receive.equals("quit")) break;
				System.out.println("[Client]"+receive);
				System.out.print("Insert Word(EXIT:quit):");
				send=sc.nextLine();
				pw.println(send);
				if(send.equals("quit")) break; 
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sc.close();
			pw.close();
			try {
				if(br!=null) br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(s!=null) s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(ss!=null) ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
