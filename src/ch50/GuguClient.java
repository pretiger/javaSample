package ch50;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.event.ItemListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.event.ItemEvent;

public class GuguClient extends JFrame {

	private JPanel contentPane;
	private JTextArea ta;
	private JComboBox cbDan;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuguClient frame = new GuguClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuguClient() {
		
		//서버에 접속
		try {
			socket=new Socket("localhost", 9999);
			dis=new DataInputStream(socket.getInputStream());
			dos=new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 356, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("단을 선택하세요");
		lblNewLabel.setBounds(12, 10, 89, 15);
		contentPane.add(lblNewLabel);
		
		cbDan = new JComboBox();
		cbDan.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					int dan=Integer.parseInt(cbDan.getSelectedItem().toString());
					try {
						dos.writeInt(dan);
						ta.setText(dis.readUTF());
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		cbDan.setModel(new DefaultComboBoxModel(new String[] {"2", "3", "4", "5", "6", "7", "8", "9"}));
		cbDan.setBounds(118, 6, 32, 23);
		contentPane.add(cbDan);
		
		ta = new JTextArea();
		ta.setBounds(12, 35, 316, 217);
		contentPane.add(ta);
	}
}
