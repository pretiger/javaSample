package aboutDB;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ScoreRemakeSave extends JFrame {

	private JPanel contentPane;
	private JTextField tfStudent_no;
	private JTextField tfName;
	private JTextField tfKor;
	private JTextField tfEng;
	private JTextField tfMat;
	private ScoreDTO dto;
	private ScoreDAO dao;
	private ScoreRemakeMain parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreRemakeSave frame = new ScoreRemakeSave();
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
	public ScoreRemakeSave(ScoreRemakeMain parent) {
		this();
		this.parent=parent;
	}
	public ScoreRemakeSave() {
		dao=new ScoreDAO();
		dto=new ScoreDTO();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 271, 255);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("학번");
		lblNewLabel.setBounds(28, 16, 57, 15);
		contentPane.add(lblNewLabel);
		
		tfStudent_no = new JTextField();
		tfStudent_no.setBounds(113, 13, 116, 21);
		contentPane.add(tfStudent_no);
		tfStudent_no.setColumns(10);
		
		JLabel label = new JLabel("이름");
		label.setBounds(28, 44, 57, 15);
		contentPane.add(label);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(113, 41, 116, 21);
		contentPane.add(tfName);
		
		JLabel label_1 = new JLabel("국어");
		label_1.setBounds(28, 72, 57, 15);
		contentPane.add(label_1);
		
		tfKor = new JTextField();
		tfKor.setColumns(10);
		tfKor.setBounds(113, 69, 116, 21);
		contentPane.add(tfKor);
		
		JLabel label_2 = new JLabel("영어");
		label_2.setBounds(28, 100, 57, 15);
		contentPane.add(label_2);
		
		tfEng = new JTextField();
		tfEng.setColumns(10);
		tfEng.setBounds(113, 97, 116, 21);
		contentPane.add(tfEng);
		
		JLabel label_3 = new JLabel("수학");
		label_3.setBounds(28, 128, 57, 15);
		contentPane.add(label_3);
		
		tfMat = new JTextField();
		tfMat.setColumns(10);
		tfMat.setBounds(113, 125, 116, 21);
		contentPane.add(tfMat);
		
		JButton btnSave = new JButton("저장");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input();
				int result=dao.insertScore(dto);
				if(result != 0) {
					JOptionPane.showMessageDialog(ScoreRemakeSave.this, "저장 완료");
					parent.refreshTable();
					dispose();
				}
				
			}
		});
		btnSave.setBounds(73, 176, 97, 23);
		contentPane.add(btnSave);
	}
	
	void input() {
		String student_no=tfStudent_no.getText();
		String name=tfName.getText();
		int kor=Integer.parseInt(tfKor.getText());
		int eng=Integer.parseInt(tfEng.getText());
		int mat=Integer.parseInt(tfMat.getText());
		int tot=kor+eng+mat;
		double avg=tot/3.0;
		dto=new ScoreDTO(student_no, name, kor, eng, mat, tot, avg);
	}
	
}