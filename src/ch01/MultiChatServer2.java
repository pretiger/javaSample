package ch01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class MultiChatServer2 {
	HashMap<String, Object> userMap;

	public MultiChatServer2() {
		ServerSocket ss=null;
		Socket s=null;
		
		userMap=new HashMap<>();
		Collections.synchronizedMap(userMap);
		
		try {
			ss=new ServerSocket(9100);
			System.out.println("MultiChatServer2 Start");
			while(true) {
				s=ss.accept();
				System.out.println("["+s.getInetAddress()+"]에서 입장하셨습니다.");
				SocketReceiver sr=new SocketReceiver(s);
				sr.start();
				System.out.println("현재 실행중인 쓰레드 : "+sr.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new MultiChatServer2();
	}
	
	class SocketReceiver extends Thread {
		Socket s;
		DataInputStream dis;
		DataOutputStream dos;
		
		SocketReceiver(Socket s) {
			this.s=s;
			try {
				dis=new DataInputStream(s.getInputStream());
				dos=new DataOutputStream(s.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			String name="";
			try {
				name=dis.readUTF();
				userMap.put(name, dos);
				sendToAll("["+name+"]님이 입장 하셨습니다.");
				System.out.println("현재 접속자 수 : "+userMap.size());
				while(dis != null) {
					sendToAll(dis.readUTF());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sendToAll("#"+name+"님이 퇴장하셨습니다.");
				userMap.remove(name);
				System.out.println("["+s.getInetAddress()+"] 에서 퇴장하셨습니다.");
				System.out.println("현재 접속자 수 : "+userMap.size());
			}
		}

		void sendToAll(String msg) {
			Iterator<String> it=userMap.keySet().iterator();
			while(it.hasNext()) {
				DataOutputStream dos=(DataOutputStream)userMap.get(it.next());
				try {
					dos.writeUTF(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
