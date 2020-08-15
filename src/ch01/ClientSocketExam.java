package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientSocketExam {
	public static void main(String[] args) {
		try {
			Socket s=new Socket("localhost", 9100);
			BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
			String str=br.readLine();
			System.out.println(str);
			s.close();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
