package aboutDB;

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
	private ScoreDTO dto;
	private ScoreDAO dao;
	private ScoreRemote parent;

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
	public ScoreEdit(ScoreRemote parent, ScoreDTO dto) {
		this();
		this.parent=parent;
		this.dto=dto;
		tfStudent_no.setText(dto.getStudent_no());
		tfName.setText(dto.getName());
		tfKor.setText(dto.getKor()+"");
		tfEng.setText(dto.getEng()+"");
		tfMat.setText(dto.getMat()+"");
	}
	
	public ScoreEdit() {
		dao=new ScoreDAO();
		dto=new ScoreDTO();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 282, 273);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("학번");
		lblNewLabel.setBounds(24, 24, 57, 15);
		contentPane.add(lblNewLabel);
		
		tfStudent_no = new JTextField();
		tfStudent_no.setEditable(false);
		tfStudent_no.setBounds(120, 21, 116, 21);
		contentPane.add(tfStudent_no);
		tfStudent_no.setColumns(10);
		
		JLabel label = new JLabel("이름");
		label.setBounds(24, 55, 57, 15);
		contentPane.add(label);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(120, 52, 116, 21);
		contentPane.add(tfName);
		
		JLabel label_1 = new JLabel("국어");
		label_1.setBounds(24, 86, 57, 15);
		contentPane.add(label_1);
		
		tfKor = new JTextField();
		tfKor.setColumns(10);
		tfKor.setBounds(120, 83, 116, 21);
		contentPane.add(tfKor);
		
		JLabel label_2 = new JLabel("영어");
		label_2.setBounds(24, 117, 57, 15);
		contentPane.add(label_2);
		
		tfEng = new JTextField();
		tfEng.setColumns(10);
		tfEng.setBounds(120, 114, 116, 21);
		contentPane.add(tfEng);
		
		JLabel label_3 = new JLabel("수학");
		label_3.setBounds(24, 148, 57, 15);
		contentPane.add(label_3);
		
		tfMat = new JTextField();
		tfMat.setColumns(10);
		tfMat.setBounds(120, 145, 116, 21);
		contentPane.add(tfMat);
		
		JButton btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input();
				int result=dao.updateScore(dto);
				if(result != 0) {
					JOptionPane.showMessageDialog(ScoreEdit.this, "수정 완료");
					parent.refreshList();
					dispose();
				}
			}
		});
		btnUpdate.setBounds(30, 188, 97, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result=0;
				String student_no=tfStudent_no.getText();
				result=JOptionPane.showConfirmDialog(ScoreEdit.this, "데이터를 삭제 하시겠습니까?");
				if(result==JOptionPane.YES_OPTION)	{
					result=dao.deleteScore(student_no);
				} else return;
				if(result!=0) {
					JOptionPane.showMessageDialog(ScoreEdit.this, "삭제 완료");
					parent.refreshList();
					dispose();
				}
			}
		});
		btnDelete.setBounds(139, 188, 97, 23);
		contentPane.add(btnDelete);
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
