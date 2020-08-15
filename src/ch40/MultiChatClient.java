package ch40;

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
	JPanel pan;
	JScrollPane scroll;
	JLabel lbl;
	JTextField tf;
	JTextArea ta;
	String nickName;
	DataOutputStream dos;
	
	public MultiChatClient() {
		nickName=JOptionPane.showInputDialog("대화명을 입력하세요!");
		new ChatScreen();
		try {
			Socket socket=new Socket("localhost", 9100);
			ReceiveS rs=new ReceiveS(socket);
			rs.start();
			SendS ss=new SendS(socket, nickName);
			ss.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	class ReceiveS extends Thread {
		Socket socket;
		DataInputStream dis;
		
		ReceiveS(Socket socket){
			this.socket=socket;
			try {
				dis=new DataInputStream(socket.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			try {
				while(dis != null) {
					ta.append(dis.readUTF()+"\n");
					ta.setCaretPosition(ta.getDocument().getLength());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	class SendS extends Thread {
		Socket socket;
		String nick;
		
		SendS(Socket socket, String nickName){
			this.socket=socket;
			nick=nickName;
			
			try {
				dos=new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			try {
				dos.writeUTF(nick);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new MultiChatClient();
	}
	
	class ChatScreen extends JFrame implements ActionListener {

		ChatScreen(){
			setSize(300,300);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setTitle(nickName+"님의 대화방");
			pan=new JPanel();
			scroll=new JScrollPane();
			lbl=new JLabel(nickName+"님");
			tf=new JTextField();
			tf.addActionListener(this);
			ta=new JTextArea();
			add(scroll,"Center");
			scroll.setViewportView(ta);
			pan.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			pan.setLayout(new BorderLayout());
			pan.add(lbl,"West");
			pan.add(tf,"Center");
			add(pan,"South");
			setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==tf) {
				try {
					dos.writeUTF("["+nickName+"]"+tf.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				tf.setText("");
			}
		}
		
	}

}
