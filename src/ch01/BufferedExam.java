package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedExam {
	public static void main(String[] args) {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		String str=null;
		System.out.println("입력하세요.");
		try {
			str=reader.readLine();
			System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
