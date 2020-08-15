package aboutNetwork;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExam {
	public static void main(String[] args) {
		try {
			InetAddress ia=InetAddress.getByName("daum.net");
			System.out.println(ia);
			System.out.println(ia.getHostName());
			System.out.println(ia.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
