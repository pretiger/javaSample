package aboutNetwork;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadImage {
	public static void main(String[] args) {
		String website="https://www.google.co.kr/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";
		System.out.println("이미지를 다운로드 합니다.");
		URL url=null;
		try {
			url = new URL(website);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		byte[] buffer=new byte[2048];
		try(InputStream in=url.openStream();
				OutputStream out=new FileOutputStream("e:\\test.png")) {
			int length=0;
			while((length=in.read(buffer))!=-1) { //InputStream에서 read하여 buffer에 저장, 더 이상 읽을 내용이 없으면 -1
				System.out.println(length+"바이트 읽음");
				out.write(buffer,0,length); //0부터 length만큼 buffer에 내용을 test.png에 기록한다.
			}
			System.out.println("다운로드가 완료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
