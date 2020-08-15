package ch41;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class LoginExam extends JFrame {

	private JPanel contentPane;
	private JTextField userid;
	private JPasswordField pwd;
	private JLabel lblResult;
	private Map<String,String> map;

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
		
		userid = new JTextField();
		userid.setBounds(174, 37, 116, 21);
		contentPane.add(userid);
		userid.setColumns(10);
		
		pwd = new JPasswordField();
		pwd.setBounds(174, 98, 116, 21);
		contentPane.add(pwd);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(48, 40, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(48, 101, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		lblResult = new JLabel("New label");
		lblResult.setBounds(48, 202, 298, 15);
		contentPane.add(lblResult);
		
		map=new HashMap<>();
		map.put("kim", "1234");
		map.put("park", "2222");
		map.put("hong", "3333");
		
		JButton btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//텍스트 필드의 입력내용 읽기
				String id=userid.getText();
				//JPasswordField의 값을 얻을 때는 getPassword()를 권장사용
				//패스워드필드의 입력값을 읽기, 입력값은 char[]로 리턴
				//String.valueof(문자배열) => String으로 전환
				String pw=String.valueOf(pwd.getPassword());
				//map.get("kim" or key) => value 리턴
				String strId=map.get(id);
				if(strId != null && strId.equals(pw)) {
					//투명하게 설정, 기본값이 false JLabel에 색상설정이 보이도록 하기위해
					lblResult.setOpaque(true);
					//JLabel에 글자색을 지정
					lblResult.setForeground(Color.blue);
					//배경색을 지정
					lblResult.setBackground(Color.yellow);
					lblResult.setText(id+"님 환영합니다.");
				}else {
					lblResult.setOpaque(true);
					lblResult.setForeground(Color.red);
					lblResult.setBackground(Color.cyan);
					lblResult.setText("ID 또는 비밀번호가 일치하지 않습니다.");
				}
			}
		});
		btnLogin.setBounds(174, 154, 97, 23);
		contentPane.add(btnLogin);
	}
}
