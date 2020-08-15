package aboutFileInOut;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class DataStreamExam {
	public static void main(String[] args) {
		DataOutputStream dos=null;
		DataInputStream dis=null;
		try {
			dos=new DataOutputStream(new FileOutputStream("e:\\a.dat"));
			dos.writeInt(1234);
			dos.writeDouble(0.241556);
			dos.writeBoolean(true);
			dos.writeUTF("테스트입니다.");
			dos.close();
			System.out.println("작업이 종료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			dis=new DataInputStream(new FileInputStream("e:\\a.dat"));
			System.out.println(dis.readInt());
			System.out.println(dis.readDouble());
			System.out.println(dis.readBoolean());
			System.out.println(dis.readUTF());
			dis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
