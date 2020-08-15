package ch50;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileWrite {
	public static void main(String[] args) {
		OutputStream os=null;
		try {
			os=new FileOutputStream("e:\\test.txt");
			System.out.println("입력하세요!");
			while(true) {
				int ch;
				try {
					ch = System.in.read();//1바이트 읽음
					if(ch==13) break;//엔터키이면 종료
					os.write(ch);//파일에 기록
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("저장되었습니다.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
