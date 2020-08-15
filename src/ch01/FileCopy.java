package ch01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileCopy {
	public static void main(String[] args) {
		BufferedReader reader=null;
		BufferedWriter writer=null;
		String file1="e:\\test.txt";
		String file2="e:\\test2.txt";
		
		try {
			reader=new BufferedReader(new InputStreamReader(new FileInputStream(file1),"ms949"));
			writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2),"ms949"));
			while(true) {
				String str=reader.readLine();
				writer.write(str+"\r\n");
				if(str==null) break;
			}
			System.out.println("작업이 완료되었습니다.");
		} catch (Exception e) {
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
