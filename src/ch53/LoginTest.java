package ch53;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class LoginTest extends JFrame {

	private JPanel contentPane;
	private JTextField userid;
	private JPasswordField pwd;
	private JLabel lblResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginTest frame = new LoginTest();
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
	public LoginTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(12, 20, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(12, 63, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		userid = new JTextField();
		userid.setBounds(95, 17, 116, 21);
		contentPane.add(userid);
		userid.setColumns(10);
		
		pwd = new JPasswordField();
		pwd.setBounds(95, 60, 116, 21);
		contentPane.add(pwd);
		
		JButton btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strUserid=userid.getText();
				String strPwd=String.valueOf(pwd.getPassword());
				System.out.println("아이디:"+strUserid);
				System.out.println("비밀번호:"+strPwd);
				
				Connection conn=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				try {
					FileInputStream fis=new FileInputStream("e:\\db.prop");
					Properties prop=new Properties();
					prop.load(fis);
					String url=prop.getProperty("url");
					String id=prop.getProperty("id");
					String password=prop.getProperty("password");
					conn=DriverManager.getConnection(url, id, password);
					String sql="select * from tblMember where userid=? and pwd=?";
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, strUserid);
					pstmt.setString(2, strPwd);
					rs=pstmt.executeQuery();
					if(rs.next()) {
						lblResult.setText(rs.getString("name")+"님 환영합니다.");
					}else {
						lblResult.setText("아이디 또는 비밀번호가 일치하지 않습니다.");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				} finally {
					try {
						if(rs != null) rs.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					try {
						if(pstmt != null) pstmt.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					try {
						if(conn != null) conn.close();
					} catch (Exception e3) {
						e3.printStackTrace();
					}
				}
			}
		});
		btnLogin.setBounds(114, 108, 97, 23);
		contentPane.add(btnLogin);
		
		lblResult = new JLabel("New label");
		lblResult.setBounds(12, 162, 361, 15);
		contentPane.add(lblResult);
	}
}
