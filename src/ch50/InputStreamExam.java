package ch50;

import java.io.IOException;

public class InputStreamExam {
	public static void main(String[] args) {
		int var=0;
		
		System.out.println("내용를 입력하세요!");
		//System.in => InputStream
		try {
			var=System.in.read();//1byte를 읽음
			while(var != 13) {//엔터키(ASII=13)를 입력할때까지 반복
				System.out.println((char)var+"==> "+var);
				var=System.in.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
