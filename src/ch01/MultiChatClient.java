package ch01;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiChatClient extends JFrame {
	static String nickName;
	static JTextArea ta;
	static JTextField tf;
	static DataOutputStream dos;
	
	public static void main(String[] args) {
		nickName=JOptionPane.showInputDialog("대화명을 입력하세요");
		new ClientScreen(nickName);
		Socket s;
		String serverIP="localhost";
		try {
			s=new Socket(serverIP, 9100);
			System.out.println("서버에 연결 되었습니다.");
			Thread receiveThread=new ClientReceiver(s);
			receiveThread.start();
			Thread sender=new ClientSender(s, nickName);
			sender.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static class ClientSender extends Thread {
		Socket s;
		String name;
		ClientSender(Socket socket, String name) {
			this.s=socket;
			try {
				dos=new DataOutputStream(s.getOutputStream());
				this.name=name;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
//			Scanner scanner=new Scanner(System.in);
			try {
				if(dos != null) dos.writeUTF(name);
//				while(dos != null) {
//					dos.writeUTF("["+name+"]"+scanner.nextLine());
//				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	static class ClientReceiver extends Thread {
		Socket s;
		DataInputStream dis;
		
		ClientReceiver(Socket socket){
			this.s=socket;
			try {
				dis=new DataInputStream(s.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			while(dis != null) {
				try {
					String str=dis.readUTF();
					System.out.println(str);
					ta.append(str+"\n");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	static class ClientScreen extends JFrame implements ActionListener {
		public ClientScreen(String nick) {
			setSize(300,300);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setTitle(nick+"의 채팅방 클라이언트");
//			setLayout(new BorderLayout());  //기본 Frame의 layout 이므로 주석처리
			ta=new JTextArea(25,40);
			tf=new JTextField(25);
			tf.addActionListener(this);
			JPanel pan=new JPanel();
			pan.setLayout(new BorderLayout());
			pan.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			JLabel label=new JLabel(nick+"님");
			pan.add(label, "West");
			pan.add(tf, "Center");
			add(ta, "Center");
			add(pan, "South");
//			pan.add(label, BorderLayout.WEST);  //위 내용과 동일한 효과이므로 코딩양이 적은쪽으로 처리
//			pan.add(tf, BorderLayout.CENTER);
//			add(ta, BorderLayout.CENTER);
//			add(pan, BorderLayout.SOUTH);
			setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == tf) {
				try {
					dos.writeUTF("["+nickName+"]"+tf.getText());
					tf.setText("");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
	}
	
}
