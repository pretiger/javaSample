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

public class ScoreEdit extends JFrame {

	private JPanel contentPane;
	private JTextField tfStudent_no;
	private JTextField tfName;
	private JTextField tfKor;
	private JTextField tfEng;
	private JTextField tfMat;
	private ScoreList parent;
	private ScoreDAO dao;
	private ScoreDTO dto;
	private int result;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreEdit frame = new ScoreEdit();
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
	public ScoreEdit(ScoreList parent, ScoreDTO dto) {
		this();
		this.parent=parent;
		tfStudent_no.setText(dto.getStudent_no());
		tfName.setText(dto.getName());
		tfKor.setText(dto.getKor()+"");
		tfEng.setText(dto.getEng()+"");
		tfMat.setText(dto.getMat()+"");
		setLocation(300,400);
	}
	
	public ScoreEdit() {
		setTitle("점수 수정/삭제");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 240, 261);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("학번");
		lblNewLabel.setBounds(12, 25, 57, 15);
		contentPane.add(lblNewLabel);
		
		tfStudent_no = new JTextField();
		tfStudent_no.setEditable(false);
		tfStudent_no.setBounds(92, 22, 116, 21);
		contentPane.add(tfStudent_no);
		tfStudent_no.setColumns(10);
		
		JLabel label = new JLabel("이름");
		label.setBounds(12, 56, 57, 15);
		contentPane.add(label);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(92, 53, 116, 21);
		contentPane.add(tfName);
		
		JLabel label_1 = new JLabel("국어");
		label_1.setBounds(12, 84, 57, 15);
		contentPane.add(label_1);
		
		tfKor = new JTextField();
		tfKor.setColumns(10);
		tfKor.setBounds(92, 81, 116, 21);
		contentPane.add(tfKor);
		
		JLabel label_2 = new JLabel("영어");
		label_2.setBounds(12, 112, 57, 15);
		contentPane.add(label_2);
		
		tfEng = new JTextField();
		tfEng.setColumns(10);
		tfEng.setBounds(92, 109, 116, 21);
		contentPane.add(tfEng);
		
		JLabel label_3 = new JLabel("수학");
		label_3.setBounds(12, 140, 57, 15);
		contentPane.add(label_3);
		
		tfMat = new JTextField();
		tfMat.setColumns(10);
		tfMat.setBounds(92, 137, 116, 21);
		contentPane.add(tfMat);
		
		JButton btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String student_no=tfStudent_no.getText();
				String name=tfName.getText();
				int kor=Integer.parseInt(tfKor.getText()+"");
				int eng=Integer.parseInt(tfEng.getText()+"");
				int mat=Integer.parseInt(tfMat.getText()+"");
				dto=new ScoreDTO(student_no, name, kor, eng, mat);
				dao=new ScoreDAO();
				result=dao.updateScore(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(null, "수정 완료");
					parent.refreshTable();
					dispose();
				}
			}
		});
		btnUpdate.setBounds(12, 179, 97, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result=JOptionPane.showConfirmDialog(null, "삭제 하시겠습니까?",null, JOptionPane.YES_NO_OPTION);
//				result=JOptionPane.showConfirmDialog(ScoreEdit.this, "삭제 하시겠습니까?");
				if(result==JOptionPane.YES_OPTION) {
					String student_no=tfStudent_no.getText();
					dao=new ScoreDAO();
					dao.deleteScore(student_no);
					//JOptionPane.showMessageDialog(null, "삭제 되었습니다.");
					parent.refreshTable();
					dispose();
				}
			}
		});
		btnDelete.setBounds(121, 179, 97, 23);
		contentPane.add(btnDelete);
	}
}
