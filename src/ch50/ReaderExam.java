package ch50;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
//InputStream : 바이트 단위로 입력
//InputStreamReader : 문자단위로 입력
public class ReaderExam {
	public static void main(String[] args) {
		int var=0;
		//new InputStreamReader(InputStream객체)
		Reader input=new InputStreamReader(System.in);
		try {
			System.out.println("내용을 입력하세요~!");
			while(true) {
				var=input.read();//1개의 문자를 읽음
				if(var == 13) break;//엔터키(13)을 만나면 종료
				System.out.println((char)var+"=>"+var);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
