package ch01;

import java.io.IOException;

public class InputStreamExam {
	public static void main(String[] args) {
		int ch=0;
		try {
			System.out.println("문자를 입력하세요.");
			ch=System.in.read();
			while(true) {
				System.out.println((char)ch+":"+ch );
				if(ch==13) break;
				ch=System.in.read();
//				System.out.println((char)ch+":"+ch);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
