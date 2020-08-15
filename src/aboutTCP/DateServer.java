package aboutTCP;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateServer {
	public static void main(String[] args) {
		ServerSocket ss=null;
		Socket socket=null;
		try {
			ss=new ServerSocket(9100);
			System.out.println("DateServer가 시작되었습니다.");
			while(true) {
				socket=ss.accept();
				System.out.println("클라인언트 ip:"+socket.getInetAddress());
				PrintWriter pw=new PrintWriter(socket.getOutputStream(), true);
//				BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//				이부분을 사용하면 클라이언트쪽에서 받지를 못함
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
				String str=sdf.format(new Date());
				pw.println(str);
//				bw.write(str);
//				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(socket!=null) socket.close();
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
