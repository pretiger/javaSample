package ch04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class MultiChatServer {
	HashMap<String, Object> userMap;
	
	public MultiChatServer() {
		ServerSocket serverS=null;
		Socket socket=null;
		
		userMap=new HashMap<>();
		Collections.synchronizedMap(userMap);
		
		try {
			serverS=new ServerSocket(9100);
			System.out.println("Server Start");
			while(true) {
				socket=serverS.accept();
				System.out.println("Client IP:"+socket.getInetAddress());
				ReceiverTh th=new ReceiverTh(socket);
				th.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	class ReceiverTh extends Thread {
		Socket socket;
		DataInputStream dis;
		DataOutputStream dos;
		
		public ReceiverTh(Socket sc) {
			socket=sc;
			try {
				dis=new DataInputStream(socket.getInputStream());
				dos=new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			String name="";
			try {
				name=dis.readUTF();
				userMap.put(name,dos);
				sendToAll("@"+name+"님이 입장");
				while(dis != null) {
					sendToAll(dis.readUTF());
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				sendToAll("@"+name+"님이 퇴장");
				userMap.remove(name);
				System.out.println("현재 접속자수:"+userMap.size());
			}
		}
		void sendToAll(String msg) {
			Iterator<String> it=userMap.keySet().iterator();
			while(it.hasNext()) {
				dos=(DataOutputStream)userMap.get(it.next());
				try {
					dos.writeUTF(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new MultiChatServer();
	}
}
