package aboutFileInOut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BufferedReaderExam {
	public static void main(String[] args) {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("내용을 입력하세요.");
			String str=br.readLine();//한 라인을 읽음
			System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
