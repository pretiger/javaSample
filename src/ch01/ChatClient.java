package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	public static void main(String[] args) {
		Socket s=null;
		BufferedReader br=null;
		PrintWriter pw=null;
		Scanner sc=null;
		
		try {
			s=new Socket("localhost",9100);
			br=new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw=new PrintWriter(s.getOutputStream(), true);
			sc=new Scanner(System.in);
			while(true) {
				String receive=br.readLine();
				System.out.println("[Server]"+receive);
				if(receive.equals("quit")) break;
				System.out.print("Insert Word(EXIT:quit):");
				String send=sc.nextLine();
				if(send != null) pw.println(send);
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
		}
	}
}
