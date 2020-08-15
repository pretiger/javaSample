package aboutNetwork;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExam2 {
	public static void main(String[] args) {
		try {
			InetAddress[] ia=InetAddress.getAllByName("daum.net");
			for(int i=0;i<ia.length;i++) {
				System.out.println(ia[i]);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
}
