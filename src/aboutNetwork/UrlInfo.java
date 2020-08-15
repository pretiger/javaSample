package aboutNetwork;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlInfo {
	public static void main(String[] args) {
		try {
			URL url=new URL("https://en.dict.naver.com/#/search?query=enumeration");
			System.out.println("호스트명:"+url.getHost());
			System.out.println("포트번호:"+url.getPort());
			System.out.println("프로토콜:"+url.getProtocol());
			System.out.println("파일:"+url.getFile());
			System.out.println("기타정보:"+url.toExternalForm());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
}
