package ch40;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class MultiChatServer {
	HashMap<String, Object> userMap;
	
	public MultiChatServer() {
		ServerSocket serverS=null;
		Socket clientS=null;
		
		userMap=new HashMap<>();
		Collections.synchronizedMap(userMap);
		
		try {
			serverS=new ServerSocket(9100);
			System.out.println("MultiChatServer를 시작합니다.");
			while(true) {
				clientS=serverS.accept();
				System.out.println("클라이언트 ip:"+clientS.getInetAddress().getHostAddress());
				ReceiverS rs=new ReceiverS(clientS);
				rs.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	class ReceiverS extends Thread {
		Socket socket;
		DataInputStream dis;
		DataOutputStream dos;
		
		ReceiverS(Socket sc){
			socket=sc;
			try {
				dis=new DataInputStream(socket.getInputStream());
				dos=new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			String name="";
			try {
				name=dis.readUTF();
				userMap.put(name,dos);
				sendToAll("#"+name+"님이 입장하셨습니다.");
				while(dis != null) {
					sendToAll(dis.readUTF());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	finally {
				sendToAll("#"+name+"님이 퇴장하셨습니다.");
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new MultiChatServer();
	}
}
