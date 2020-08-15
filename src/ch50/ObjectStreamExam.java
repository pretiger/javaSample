package ch50;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectStreamExam {
	public static void main(String[] args) {
		//객체 직열화
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;
		MemberDTO m1=new MemberDTO("kim", 30, "785925-1256235", "1234");
		MemberDTO m2=new MemberDTO("park", 28, "785925-1256235", "2222");
		MemberDTO m3=new MemberDTO("hong", 35, "785925-1256235", "3333");
		try {
			fos=new FileOutputStream("e:\\object.dat");
			oos=new ObjectOutputStream(fos);//객체 직열화 처리 객체
			oos.writeObject(m1);//객체를 파일로 저장
			oos.writeObject(m2);
			oos.writeObject(m3);
			System.out.println("객체를 저장했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fos != null) fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(oos != null) oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//역직열화(파일에 저장된 객체를 다시 메모리로 불러옴
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		try {
			fis=new FileInputStream("e:\\object.dat");
			ois=new ObjectInputStream(fis);
			MemberDTO dto1=(MemberDTO)ois.readObject();
			MemberDTO dto2=(MemberDTO)ois.readObject();
			MemberDTO dto3=(MemberDTO)ois.readObject();
			System.out.println(dto1);
			System.out.println(dto2);
			System.out.println(dto3);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fis != null) fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(ois != null) ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
