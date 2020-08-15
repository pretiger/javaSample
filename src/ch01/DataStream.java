package ch01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DataStream {
	public static void main(String[] args) {
		try {
			DataOutputStream dos=new DataOutputStream(new FileOutputStream("e:\\test1.txt"));
			dos.writeInt(100);
			dos.writeDouble(200.2);
			dos.writeUTF("JAVA WORLD");
			dos.close();
			System.out.println("저장완료");
			DataInputStream dis=new DataInputStream(new FileInputStream("e:\\test1.txt"));
			System.out.println(dis.readInt());
			System.out.println(dis.readDouble());
			System.out.println(dis.readUTF());
			dis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
