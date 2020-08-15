package ch01;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginExam extends JFrame {

	private JPanel contentPane;
	private JTextField tfID;
	private JPasswordField pfdPW;
	private JLabel lbStatus;
	private Map<String, String> map;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginExam frame = new LoginExam();
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
	public LoginExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfID = new JTextField();

		tfID.setBounds(121, 40, 116, 21);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		pfdPW = new JPasswordField();
		pfdPW.setBounds(121, 97, 116, 21);
		contentPane.add(pfdPW);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(30, 43, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(30, 100, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		lbStatus = new JLabel("New label");
		lbStatus.setBounds(121, 220, 239, 15);
		contentPane.add(lbStatus);
		
		map=new HashMap<>();
		map.put("kim", "1234");
		map.put("park", "1234");
		map.put("jeong", "1234");
		
		JButton btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=tfID.getText();
				System.out.println("아이디 : "+id);
				String pw=String.valueOf(pfdPW.getPassword());
				System.out.println("비밀번호 : "+pw);
				String StrPwd=map.get(id);
				System.out.println("비빌번호 확인 : "+StrPwd);
				if(StrPwd != null && StrPwd.equals(pw)) {
					lbStatus.setOpaque(true);
					lbStatus.setBackground(Color.yellow);
					lbStatus.setText(id+"님 환영합니다.");
				}else {
					lbStatus.setForeground(Color.red);
					lbStatus.setText("아이디 또는 비밀번호가 일치하지 않습니다.");
				}	
			}
		});
		btnLogin.setBounds(121, 149, 97, 23);
		contentPane.add(btnLogin);
	}
}
