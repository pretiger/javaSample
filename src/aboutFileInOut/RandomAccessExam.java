package aboutFileInOut;

import java.io.RandomAccessFile;

public class RandomAccessExam {
	public static void main(String[] args) {
		StringBuilder sb=new StringBuilder();
		try {
			RandomAccessFile raf=new RandomAccessFile("e:\\test1.txt","rw");//읽기, 쓰기 가능한 비순차접근파일
			
			raf.seek(20);//파일 포인터 이동
			raf.write("추가한 문자열".getBytes("ms949"));//문자열을 바이트배열로 변환한 후 파일에 저장
			raf.seek(0);
			
			while(raf.getFilePointer()<raf.length()) { //파일포인터(파일에서 읽을 위치를 가리키는 포인터)
				sb.append(raf.getFilePointer()+":");
				String str=raf.readLine();
				//new String(바이트배열,문자셋) 인코딩 변환 ==>한글 처리를 위한 작업
				str=new String(str.getBytes("8859_1"), "ms949");//iso-8859-1      서유럽언어
				sb.append(str+"\r\n");							//ksc5601, euc-kr, ms949, cp949      한국어
			}													//utf-8        다국어 
			raf.close();
			System.out.println(sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
