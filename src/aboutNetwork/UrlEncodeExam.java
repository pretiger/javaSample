package aboutNetwork;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlEncodeExam {
	public static void main(String[] args) {
		try {
			System.out.println(									  //한글, 특수문자는 url에서 사용할 수 없음.	
//					URLEncoder.encode("my name is kim","utf-8")); //url에 포함된 한글, 특수문자는 url encoding해야함.
					URLEncoder.encode("나는 김철수 입니다.","utf-8"));
			System.out.println(									  //encoding된 것을 한글,특수문자를 복구하려면 decoding해야함.
					URLDecoder.decode("%EB%82%98%EB%8A%94+%EA%B9%80%EC%B2%A0%EC%88%98+%EC%9E%85%EB%8B%88%EB%8B%A4.","utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
