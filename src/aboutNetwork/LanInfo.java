package aboutNetwork;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class LanInfo {
	public static void main(String[] args) {
		try {
			Enumeration<NetworkInterface> enu=NetworkInterface.getNetworkInterfaces();
			while(enu.hasMoreElements()) {
				NetworkInterface net=enu.nextElement();
				System.out.println(net);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}
}
