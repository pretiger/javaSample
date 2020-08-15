package aboutNetwork;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class InetInfo {

	public static void main(String[] args) {
		try {
			Enumeration<NetworkInterface> ni= NetworkInterface.getNetworkInterfaces();
			while(ni.hasMoreElements()) {
				NetworkInterface net=ni.nextElement();
				System.out.println(net);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
			
	}
}
