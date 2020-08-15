package aboutTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient2 {
	public static void main(String[] args) {
		Socket clientS=null;
		DataInputStream dis=null;
		DataOutputStream dos=null;
		
		try {
			clientS=new Socket("localhost", 9100);
			dis=new DataInputStream(clientS.getInputStream());
			dos=new DataOutputStream(clientS.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scan=new Scanner(System.in);
		String receive=null;
		String send=null;
		while(true) {
			try {
				receive=dis.readUTF();
				System.out.println("[서버]"+receive);
				if(receive==null || receive.equals("quit")) break;
				System.out.print("내용을 입력하세요(EXIT:quit):");
				send=scan.nextLine();
				dos.writeUTF(send);//전송 후 while문을 빠져야함.
				if(send.equals("quit")) break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			if(dos!=null) dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		scan.close();
		try {
			if(dis!=null) dis.close();
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
