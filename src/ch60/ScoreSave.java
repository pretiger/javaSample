package ch60;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ch53.ScoreDAO;
import ch53.ScoreDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScoreSave extends JFrame {

	private JPanel contentPane;
	private JTextField tfStudentNo;
	private JTextField tfName;
	private JTextField tfKor;
	private JTextField tfEng;
	private JTextField tfMat;
	//ScoreList번지값을 저장하는 변수
	private ScoreList parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreSave frame = new ScoreSave();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//ScoreList 프레임의 주소값을 받기 위한 생성자 추가
	public ScoreSave(ScoreList parent) {
		this();
		this.parent=parent;
	}
	/**
	 * Create the frame.
	 */
	public ScoreSave() {
		//전체 화면을 다 닫음
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//현재 화면만 닫음
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("학번");
		lblNewLabel.setBounds(12, 32, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setBounds(12, 67, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("국어");
		lblNewLabel_2.setBounds(12, 103, 57, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("영어");
		lblNewLabel_3.setBounds(12, 136, 57, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("수학");
		lblNewLabel_4.setBounds(12, 172, 57, 15);
		contentPane.add(lblNewLabel_4);
		
		tfStudentNo = new JTextField();
		tfStudentNo.setBounds(98, 29, 116, 21);
		contentPane.add(tfStudentNo);
		tfStudentNo.setColumns(10);
		
		tfName = new JTextField();
		tfName.setBounds(98, 67, 116, 21);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfKor = new JTextField();
		tfKor.setBounds(98, 100, 116, 21);
		contentPane.add(tfKor);
		tfKor.setColumns(10);
		
		tfEng = new JTextField();
		tfEng.setBounds(98, 133, 116, 21);
		contentPane.add(tfEng);
		tfEng.setColumns(10);
		
		tfMat = new JTextField();
		tfMat.setBounds(98, 169, 116, 21);
		contentPane.add(tfMat);
		tfMat.setColumns(10);
		
		JButton btnSave = new JButton("저장");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoreDAO dao=new ScoreDAO();
				String student_no=tfStudentNo.getText();
				String name=tfName.getText();
				int kor=Integer.parseInt(tfKor.getText());
				int eng=Integer.parseInt(tfEng.getText());
				int mat=Integer.parseInt(tfMat.getText());
				int result=dao.insertScore(new ScoreDTO(student_no, name, kor, eng, mat));
				if(result == 1) {
					JOptionPane.showMessageDialog(ScoreSave.this, "저장되었습니다.");
					//ScoreList 프레임의 method 호출
					parent.refreshTable();
					dispose();//현재 프레임을 닫음
				}
			}
		});
		btnSave.setBounds(98, 212, 97, 23);
		contentPane.add(btnSave);
	}

}
