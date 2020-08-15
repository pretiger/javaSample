package aboutFileInOut;

import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamReaderExam {
	public static void main(String[] args) {
		int var=0;
		System.out.println("내용을 입력하세요:");
		InputStreamReader reader=new InputStreamReader(System.in);
		try {
			while(var!=13) {
				var=reader.read();//InputStreamReader는 문자단위로 한문자를 읽어들임,한글로 화면 출력가능,return int
				System.out.println((char)var+"==>"+var);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
