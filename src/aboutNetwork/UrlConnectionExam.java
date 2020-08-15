package aboutNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlConnectionExam {
	public static void main(String[] args) {
		URL url=null;
		HttpURLConnection conn=null;
		BufferedReader br=null;
		try {
			url=new URL("http://google.com");
			conn=(HttpURLConnection)url.openConnection();
			System.out.println("연결정보:"+conn);
			if(conn!=null) {
				conn.setConnectTimeout(10000); //10초 동안 대기
				StringBuilder sb=new StringBuilder();
				if(conn.getResponseCode()==HttpURLConnection.HTTP_OK) {
					br=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
					while(true) {
						String line=br.readLine();
						if(line==null) break;
						sb.append(line+"\r\n");
					}
				}
				conn.disconnect();//원격 컴퓨터와 접속 종료
				System.out.println(sb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(br!=null) br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
