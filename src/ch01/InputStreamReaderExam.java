package ch01;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class InputStreamReaderExam {
	public static void main(String[] args) {
		Reader f=new InputStreamReader(System.in);
		int ch=0;
		try {
			System.out.println("문자를 입력하세요.");
			while(ch!=13) {
				ch=f.read();
				System.out.println((char)ch+"===>"+ch);
//				ch=f.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
