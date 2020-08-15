package ch02;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScoreSave extends JFrame {

	private JPanel contentPane;
	private JTextField tfStudent_no;
	private JTextField tfName;
	private JTextField tfKor;
	private JTextField tfEng;
	private JTextField tfMat;
	private ScoreList parent;
	private ScoreDTO dto;
	private ScoreDAO dao;

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

	/**
	 * Create the frame.
	 */
	public ScoreSave(ScoreList parent) {
		this();
		this.parent=parent;
		setLocation(300,400);
	}
	public ScoreSave() {
		setTitle("점수저장");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 263, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("학번");
		lblNewLabel.setBounds(26, 32, 57, 15);
		contentPane.add(lblNewLabel);
		
		tfStudent_no = new JTextField();
		tfStudent_no.setBounds(101, 29, 116, 21);
		contentPane.add(tfStudent_no);
		tfStudent_no.setColumns(10);
		
		JLabel label = new JLabel("이름");
		label.setBounds(26, 63, 57, 15);
		contentPane.add(label);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(101, 60, 116, 21);
		contentPane.add(tfName);
		
		JLabel label_1 = new JLabel("국어");
		label_1.setBounds(26, 99, 57, 15);
		contentPane.add(label_1);
		
		tfKor = new JTextField();
		tfKor.setColumns(10);
		tfKor.setBounds(101, 96, 116, 21);
		contentPane.add(tfKor);
		
		JLabel label_2 = new JLabel("영어");
		label_2.setBounds(26, 130, 57, 15);
		contentPane.add(label_2);
		
		tfEng = new JTextField();
		tfEng.setColumns(10);
		tfEng.setBounds(101, 127, 116, 21);
		contentPane.add(tfEng);
		
		JLabel label_3 = new JLabel("수학");
		label_3.setBounds(26, 161, 57, 15);
		contentPane.add(label_3);
		
		tfMat = new JTextField();
		tfMat.setColumns(10);
		tfMat.setBounds(101, 158, 116, 21);
		contentPane.add(tfMat);
		
		JButton btnSave = new JButton("저장");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String student_no=tfStudent_no.getText();
				String name=tfName.getText();
				int kor=Integer.parseInt(tfKor.getText());
				int eng=Integer.parseInt(tfEng.getText());
				int mat=Integer.parseInt(tfMat.getText());
				dto=new ScoreDTO(student_no, name, kor, eng, mat);
				dao=new ScoreDAO();
				int result=dao.insertScore(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(null, "저장되었습니다.");
					parent.refreshTable();
				}else JOptionPane.showMessageDialog(null, "학번 중복여부를 확인하세요!");
				dispose();
			}
		});
		btnSave.setBounds(62, 204, 97, 23);
		contentPane.add(btnSave);
	}
}
