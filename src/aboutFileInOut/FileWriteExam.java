package aboutFileInOut;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileWriteExam {
	public static void main(String[] args) {
		System.out.println("내용을 입력하세요.");
		try {
			FileOutputStream fos=new FileOutputStream("e:\\test.txt");
			while(true) {
				int var=System.in.read();
				if(var==13) break;
				fos.write(var);
			}
			System.out.println("저장되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
