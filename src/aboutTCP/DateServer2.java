package aboutTCP;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateServer2 {
	public static void main(String[] args) {
		ServerSocket serverS=null;
		Socket clientS=null;
		DataOutputStream dos=null;
		
		try {
			serverS=new ServerSocket(9100);
			System.out.println("DateServer2 시작");
			while(true) {
				clientS=serverS.accept();
				System.out.println("클라이언트 ip:"+clientS.getInetAddress().getHostAddress());
				dos=new DataOutputStream(clientS.getOutputStream());
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
				String str=sdf.format(new Date());
				dos.writeUTF(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(dos!=null) dos.close();
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
		}
	}
}
