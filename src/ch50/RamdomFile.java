package ch50;

import java.io.RandomAccessFile;

//비순차적 접근파일(ramdom access file) 스트림객체는 순차적 접근만 가능
//접근이 필요한 위치에 직접 파일 포인터를 이동할 수 있기 때문에 큰 크기의 파일 접근이 유리
public class RamdomFile {
	public static void main(String[] args) {
		StringBuilder output=new StringBuilder();
		String str=null;
		try {
			RandomAccessFile file=new RandomAccessFile("e:\\test.txt","rw");//읽기 쓰기가 가능한 비순차적 접근파일
			
			file.seek(15);//파일 포인터를 이동시킴
			//write(문자열.getBytes()) 문자열을 바이트배열로 변환한 후 파일에 저장
			file.write("추가한 문자열".getBytes("ms949"));
			file.seek(0);//파일 포인터를 처음으로 이동
			//파일 포인터(파일에서 읽을 위치를 가리키는 포인터)
			while(file.getFilePointer() < file.length()) {
				//파일 포인터 표시
				output.append(file.getFilePointer()+":");
				str=file.readLine();//한 줄을 읽음
				//new String(바이트배열, 문자셋) 인코딩 변환 문자열.getByte():문자열을 byte배열로 변환
//				iso-8859-1 서유럽언어
//				ksc5601, euc-kr, ms949 한국어
//				utf-8 다국어
				str=new String(str.getBytes("8859_1"), "ms949");
				output.append(str+"\r\n");//StringBuilder에 덧붙임
			}
			file.close();//파일 객체 닫음
			System.out.println(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
