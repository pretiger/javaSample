package ch50;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferReaderExam {
	public static void main(String[] args) {
		//new BufferedReader(InputStreamReader객체(InputStream객체))
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("입력하세요:");
		String str=null;
		try {
			str = reader.readLine();//한 라인을 읽
			System.out.println(str);//try catch안에서 선언한 변수(str)는 이 문장안에서만 변수로 인식
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
