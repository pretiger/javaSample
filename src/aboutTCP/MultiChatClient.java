package aboutTCP;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiChatClient {
	Socket socket;
	JTextField tf;
	JTextArea ta;
	JPanel pan;
	JLabel lbl;
	JScrollPane scroll;
	DataOutputStream dos;
	DataInputStream dis;
	String nickName;
	
	public MultiChatClient() {
		nickName=JOptionPane.showInputDialog("별칭을 입력하세요");
		new ChatScreen();
		try {
			socket=new Socket("localhost",9100);
			System.out.println("서버에 연결 되었습니다.");
			ReceiveClient rc=new ReceiveClient(socket);
			rc.start();
			SendClient sc=new SendClient(socket);
			sc.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class ReceiveClient extends Thread {
		ReceiveClient(Socket socket){
			try {
				dis=new DataInputStream(socket.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
				try {
					while(dis!=null) {  //이부분을 신경써야함. 루프를 돌면서 대화내용을 계획 화면에 뿌져준다.
						String str=dis.readUTF();
						if(str.equals("USED")) {
							JOptionPane.showMessageDialog(null, "대화명이 중복되니 다른 대화명을 사용하세요!");
							System.exit(0);
						} else {
							ta.append(str+"\n");
							ta.setCaretPosition(ta.getDocument().getLength());
							ta.setEditable(false);
							System.out.println(str);
						}
					}
				} catch (Exception e) {
				e.printStackTrace();
				}
		}
	}

	class SendClient extends Thread {
		SendClient(Socket socket){
			try {
				dos=new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
				try {
					if(dos != null)
					dos.writeUTF(nickName);//대화명을 보낼때 단 한번만 실행
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
	
	class ChatScreen  extends JFrame implements ActionListener {
		ChatScreen (){
			setSize(300,300);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setTitle(nickName+"님의 채팅방");
			lbl=new JLabel(nickName+"님");
			scroll=new JScrollPane();
			tf=new JTextField();
			tf.addActionListener(this);
			ta=new JTextArea();
			add(scroll,"Center");
			scroll.setViewportView(ta);
			pan=new JPanel();
			pan.setLayout(new BorderLayout());
			pan.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			pan.add(lbl,"West");
			pan.add(tf,"Center");
			add(pan,"South");
			setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==tf) {
				try {
					dos.writeUTF("["+nickName+"]"+tf.getText());//SendClient에서 생성한 DataOutputStream을 이용해서 데이터 전송
					tf.setText("");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new MultiChatClient();
	}
}
