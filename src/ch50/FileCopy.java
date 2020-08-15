package ch50;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileCopy {
	public static void main(String[] args) {
		String str="";
		BufferedReader reader=null;
		BufferedWriter writer=null;
		String file1="e:\\a.txt";//원본파일
		String file2="e:\\b.txt";//복사본파일
		
		try {
			reader=new BufferedReader(new InputStreamReader(new FileInputStream(file1)));
			writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2)));
//			reader=new BufferedReader(new InputStreamReader(new FileInputStream(file1),"utf-8"));
//			writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2),"utf-8"));
			while(true) {
				try {
					str=reader.readLine();//한 라인을 읽음
					if(str==null) break;//더 이상 내용이 없으면 종료
					writer.write(str+"\r\n");//파일에 기록 (줄 바꿈은 수동으로 추가)
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("파일 복사가 완료되었습니다.");
			//퍼퍼를 닫음
//			reader.close();
//			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader!=null) reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(writer!=null) writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
