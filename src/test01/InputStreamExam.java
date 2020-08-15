package test01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class InputStreamExam {

	public static void main(String[] args) {
		BufferedReader reader=null;
		BufferedWriter writer=null;
		try {
			reader=new BufferedReader(
					new InputStreamReader(new FileInputStream("e:\\upload\\aaa.txt"),"ms949"));
			writer=new  BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("e:\\upload\\ccc.txt"),"ms949"));
			while(true) {
				String str=reader.readLine();
				if(str == null) break;
				str +="\r\n";
				writer.write(str);
			}
			System.out.println("파일 복사가 완료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if( reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
