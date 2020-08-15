package aboutTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	public static void main(String[] args) {
		Socket clientS=null;
		PrintWriter pw=null;
		BufferedReader br=null;
		try {
			clientS=new Socket("localhost", 9100);
			pw=new PrintWriter(clientS.getOutputStream(),true);
			br=new BufferedReader(new InputStreamReader(clientS.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String receive=null;
		String send=null;
		Scanner scn=new Scanner(System.in);
		while(true) {
			try {
				receive=br.readLine();
				System.out.println("[서버]"+receive);
				if(receive.equals("quit")) break;
				System.out.print("내용을 입력하세요(EXIT:quit):");
				send=scn.nextLine();
				pw.println(send);
				if(send.equals("quit")) break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		pw.close();
		scn.close();
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
		
	}
}
