package aboutTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class MultiChatServer {
	HashMap<String,Object> userMap;
	
	public MultiChatServer() {
		ServerSocket serverS=null;
		Socket clientS=null;

		userMap=new HashMap<>();
		Collections.synchronizedMap(userMap);
		
		try {
			serverS=new ServerSocket(9100);
			System.out.println("MultiChatServer 시작");
			while(true) {
				clientS=serverS.accept();
				System.out.println("클라이언트 ip:"+clientS.getInetAddress().getHostAddress());
				ReceiverTh th=new ReceiverTh(clientS);
				th.start();
				System.out.println("현재 실행중인 Thread이름:"+th.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new MultiChatServer();
	}
	
	class ReceiverTh extends Thread {
		DataInputStream dis;
		DataOutputStream dos;//이부분 변수를 클래스 외부로 빼면 에러발생(전역,지역변수 특성고려)
		Socket socket;
		
		ReceiverTh(Socket clientS){
		this.socket=clientS;
			try {
				dis=new DataInputStream(socket.getInputStream());
				dos=new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {
				System.out.println("ServerReceiver Socket Error");
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			String name="";
			String compare="";
			int result=0;
			try {
				name=dis.readUTF();
				Iterator<String> it=userMap.keySet().iterator();
				while(it.hasNext()) {
					compare += (String)it.next()+":";//compare에 userMap의 key값을 저장, 이때 String구분자로 ":"를 사용
				}
//				System.out.println("userMap key값:"+compare);
				result=compare.indexOf(name);
				if(result != -1) { //name이 중복될 경우 사용하고 있음을 전달하기 위해 "USED"를 클라이언트로 전송
					dos.writeUTF("USED");
				} else { //name이 중복되지 않을 경우 진행
					userMap.put(name, dos); //해당 name을 dos와 set으로 묶음
					SendToAll("#"+name+"님이 입장하셨습니다.");
					System.out.println("현재 접속자 수 : "+userMap.size());
					while(dis!=null) { //해당 부분에서 대화가 계속 연결
						SendToAll(dis.readUTF());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(result == -1) {  //name이 중복되지 않을 경우 진행
					SendToAll("#"+name+"님이 퇴장하셨습니다.");
					userMap.remove(name);
					System.out.println("["+socket.getInetAddress().getHostAddress()+":"+socket.getPort()+"]");
					System.out.println("현재 접속자수:"+userMap.size());
				}
			}
		}
		void SendToAll(String msg) {
			Iterator<String> it=userMap.keySet().iterator();
			while(it.hasNext()) {
				dos=(DataOutputStream)userMap.get(it.next());//DataOutputSteam에 name로 연결
				try {
					dos.writeUTF(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
