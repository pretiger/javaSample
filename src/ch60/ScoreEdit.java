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

public class ScoreEdit extends JFrame {

	private JPanel contentPane;
	private JTextField tfStudentNo;
	private JTextField tfName;
	private JTextField tfKor;
	private JTextField tfEng;
	private JTextField tfMat;
	//ScoreList 주소값을 받기 뒤한 변수
	private ScoreList parent;
	//ScoreList에서 선택한 변수를 받아서 화면에 출력하기 위한 변수
	private ScoreDTO dto;

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
	
	public ScoreEdit(ScoreList parent, ScoreDTO dto){
		this(); //기본 생성자 호출
		this.parent=parent; //ScoreList 프레임의 주조값 저장
		this.dto=dto; //ScoreList에서 선택한 행을 저장
		//텍스트 필드에 값 입력
		tfStudentNo.setText(dto.getStudent_no());
		tfName.setText(dto.getName());
		tfKor.setText(dto.getKor()+"");
		tfEng.setText(dto.getEng()+"");
		tfMat.setText(dto.getMat()+"");
	}

	/**
	 * Create the frame.
	 */
	public ScoreEdit() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 261, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("학번");
		lblNewLabel.setBounds(12, 23, 57, 15);
		contentPane.add(lblNewLabel);
		
		tfStudentNo = new JTextField();
		tfStudentNo.setEditable(false);
		tfStudentNo.setBounds(101, 20, 116, 21);
		contentPane.add(tfStudentNo);
		tfStudentNo.setColumns(10);
		
		JLabel label = new JLabel("이름");
		label.setBounds(12, 64, 57, 15);
		contentPane.add(label);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(101, 61, 116, 21);
		contentPane.add(tfName);
		
		JLabel label_1 = new JLabel("국어");
		label_1.setBounds(12, 101, 57, 15);
		contentPane.add(label_1);
		
		tfKor = new JTextField();
		tfKor.setColumns(10);
		tfKor.setBounds(101, 98, 116, 21);
		contentPane.add(tfKor);
		
		JLabel label_2 = new JLabel("영어");
		label_2.setBounds(12, 147, 57, 15);
		contentPane.add(label_2);
		
		tfEng = new JTextField();
		tfEng.setColumns(10);
		tfEng.setBounds(101, 144, 116, 21);
		contentPane.add(tfEng);
		
		JLabel label_3 = new JLabel("수학");
		label_3.setBounds(12, 191, 57, 15);
		contentPane.add(label_3);
		
		tfMat = new JTextField();
		tfMat.setColumns(10);
		tfMat.setBounds(101, 188, 116, 21);
		contentPane.add(tfMat);
		
		JButton btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String student_no=tfStudentNo.getText();
				String name=tfName.getText();
				int kor=Integer.parseInt(tfKor.getText());
				int eng=Integer.parseInt(tfEng.getText());
				int mat=Integer.parseInt(tfMat.getText());
				ScoreDTO dto=new ScoreDTO(student_no, name, kor, eng, mat);
				ScoreDAO dao=new ScoreDAO();
				dao.updateScore(dto);
				parent.refreshTable();
				dispose();
			}
		});
		btnUpdate.setBounds(12, 229, 97, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				int reponse=JOptionPane.showConfirmDialog(ScoreEdit.this, "삭제하시겠습니까?");
				int reponse=JOptionPane.showConfirmDialog(
						ScoreEdit.this, "삭제하시겠습니까?", null, JOptionPane.YES_NO_OPTION);
				if(reponse == JOptionPane.YES_OPTION) {
					String student_no=tfStudentNo.getText();
					ScoreDAO dao=new ScoreDAO();
					dao.deleteScore(student_no);
					parent.refreshTable();
					dispose();
				}
			}
		});
		btnDelete.setBounds(121, 229, 97, 23);
		contentPane.add(btnDelete);
	}

}
