package aboutTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GuguServer2 {
	public static void main(String[] args) {
//		ServerSocket serverS=null;
		Socket clientS=null;
		DataInputStream dis=null;
		DataOutputStream dos=null;
		
		try(ServerSocket serverS=new ServerSocket(9100)) {
			System.out.println("GuguServer2를 시작합니다.");
			clientS=serverS.accept();
			System.out.println("클라이언트 ip:"+clientS.getInetAddress().getHostAddress());
			dos=new DataOutputStream(clientS.getOutputStream());
			dis=new DataInputStream(clientS.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		int dan=0;
		while(true) {
			try {
				dan=dis.readInt();
				System.out.println("클라이언트에서 요청한 값:"+dan);
				StringBuilder sb=new StringBuilder();
				for(int i=1;i<=9;i++) {
					sb.append(dan+"X"+i+"="+dan*i+"\r\n");
				}
				dos.writeUTF(sb.toString());
				System.out.println(sb);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
