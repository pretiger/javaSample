package ch01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//class ServerScreen extends JFrame {
//	JTextArea ta;
//	JTextField tf;
//	public ServerScreen() {
//		setSize(300,300);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setTitle("채팅방 서버");
//		JLabel label=new JLabel("This is a Server");
//		ta=new JTextArea(25,40);
//		tf=new JTextField(25);
//		add(label,"North");
//		add(ta,"Center");
//		add(tf,"South");
//		setVisible(true);
//	}
//}

public class MultiChatServer {
	HashMap userMap;
	
	public MultiChatServer() {
		userMap=new HashMap();
		Collections.synchronizedMap(userMap);
	}
	
	void serviceStart() {
		ServerSocket ss=null;
		Socket s=null;
		try {
			ss=new ServerSocket(9100);
			System.out.println("MultiChatServer Start");
			while(true) {
				s=ss.accept();
				System.out.println("["+s.getInetAddress()+"]에서 접속하셨습니다.");
				ServerReceiver receiveThread=new ServerReceiver(s);
				receiveThread.start();
				System.out.println("현재 실행중인 Thread이름:"+receiveThread.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void sendToAll(String msg) {
		Iterator it=userMap.keySet().iterator();
		while(it.hasNext()) {
			DataOutputStream dos=(DataOutputStream)userMap.get(it.next());
			try {
				dos.writeUTF(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
//		new ServerScreen();
		new MultiChatServer().serviceStart();
	}
	
	class ServerReceiver extends Thread {
		Socket s;
		DataInputStream dis;
		DataOutputStream dos;
		ServerReceiver(Socket s) {
			this.s=s;
			try {
				dis=new DataInputStream(s.getInputStream());
				dos=new DataOutputStream(s.getOutputStream());
			} catch (Exception e) {
				System.out.println("ServerReceiver Socket Error");
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			String name="";
			try {
				name=dis.readUTF();
				sendToAll("#"+name+"님이 입장하셨습니다.");
				userMap.put(name,dos);
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
	}
}
