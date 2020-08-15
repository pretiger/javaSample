package aboutFileInOut;

import java.io.IOException;

public class InputStreamExam {
	public static void main(String[] args) {
		int var=0;
		System.out.println("내용을 입력하세요");
		try {
			while(var!=13) { //화면에서 입력 받은 Enter값=13 을 만나면 while종료
				var=System.in.read();//System.in.read()는 화면상에서 입력된 문자중 첫자(1byte) 밖에는 읽어 들이지 못함
				System.out.println((char)var+" => "+var);//한글은 2byte라서 입력해도 정상적인 문자로 화면출력이 않됨
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
