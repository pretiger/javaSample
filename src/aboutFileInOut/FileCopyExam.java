package aboutFileInOut;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileCopyExam {
	public static void main(String[] args) {
		BufferedReader br=null;
		BufferedWriter bw=null;
		try {
			br=new BufferedReader(new InputStreamReader(new FileInputStream("e:\\test1.txt")));
			bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("e:\\test2.txt")));
			while(true) {
				String str=br.readLine();
				if(str==null) break;
				bw.write(str+"\r\n");
			}
			System.out.println("작업이 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw!=null) bw.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(br!=null) br.close();
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}
		
	}
}
