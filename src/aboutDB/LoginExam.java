package aboutDB;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;

public class LoginExam extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JLabel lblMsg;
	private JPasswordField pwd;

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
		setBounds(100, 100, 365, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(47, 66, 57, 15);
		contentPane.add(lblNewLabel);
		
		txtID = new JTextField();
		txtID.setBounds(165, 63, 116, 21);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel label = new JLabel("비밀번호");
		label.setBounds(47, 122, 57, 15);
		contentPane.add(label);
		
		lblMsg = new JLabel("");
		lblMsg.setBounds(47, 219, 290, 33);
		contentPane.add(lblMsg);
		
		pwd = new JPasswordField();
		pwd.setBounds(165, 119, 116, 21);
		contentPane.add(pwd);
		
		JButton btnOK = new JButton("확인");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				String name=null;
				int result=0;
				
				String userID=txtID.getText();
				String pass=String.valueOf(pwd.getPassword());
				conn=DB.getMssql();
				
				String sql="select * from tblMember where userid=? and pwd=?";
				try {
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, userID);
					pstmt.setString(2, pass);
					rs=pstmt.executeQuery();
					if(rs.next()) {
						name=rs.getString("name");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					try {
						if(rs!=null) rs.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					try {
						if(pstmt!=null) pstmt.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					try {
						if(conn!=null) conn.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				if(name != null) {
					lblMsg.setForeground(Color.BLUE);
					lblMsg.setFont(new Font("굴림", Font.PLAIN, 14));
					lblMsg.setOpaque(true);
					lblMsg.setBackground(Color.YELLOW);
					lblMsg.setText(name+"님의 로그인을 환영합니다.");
					result=JOptionPane.showConfirmDialog(LoginExam.this, 
							"점수 입력프로그램을 시작 하시겠습니까?", "YES/NO", JOptionPane.YES_NO_OPTION);
					if(result==JOptionPane.YES_OPTION) {
						ScoreRemote form=new ScoreRemote();
						form.setVisible(true);
						dispose();
					}
				} else {
					lblMsg.setForeground(Color.RED);
					lblMsg.setFont(new Font("굴림", Font.PLAIN, 14));
					lblMsg.setOpaque(true);
					lblMsg.setBackground(Color.YELLOW);
					lblMsg.setText("해당하는 아이디가 존재하지 않습니다.");
				}
				
			}
		});
		btnOK.setBounds(165, 172, 116, 23);
		contentPane.add(btnOK);
	}
}
