package aboutDB;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class ScoreExam extends JFrame {

	private JPanel contentPane;
	private Vector col, data;
	private ScoreDAO dao;
	private ScoreDTO dto;
	private DefaultTableModel model;
	private JTable table;
	private JLabel lblNewLabel;
	private JTextField tfStudent_no;
	private JLabel label;
	private JTextField tfName;
	private JLabel label_1;
	private JTextField tfKor;
	private JLabel label_2;
	private JTextField tfEng;
	private JLabel label_3;
	private JTextField tfMat;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JTextField tfSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreExam frame = new ScoreExam();
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
	public ScoreExam() {
		dao=new ScoreDAO();
		col=new Vector();
		col.add("학번");
		col.add("이름");
		col.add("국어");
		col.add("영어");
		col.add("수학");
		col.add("총점");
		col.add("평균");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 276, 483, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx=table.getSelectedRow();
				tfStudent_no.setText(table.getValueAt(idx, 0)+"");
				tfName.setText(table.getValueAt(idx, 1)+"");
				tfKor.setText(table.getValueAt(idx, 2)+"");
				tfEng.setText(table.getValueAt(idx, 3)+"");
				tfMat.setText(table.getValueAt(idx, 4)+"");
			}
		});
		scrollPane.setViewportView(table);
		
		refreshList();
		
		lblNewLabel = new JLabel("학번");
		lblNewLabel.setBounds(22, 35, 57, 15);
		contentPane.add(lblNewLabel);
		
		tfStudent_no = new JTextField();
		tfStudent_no.setBounds(123, 32, 116, 21);
		contentPane.add(tfStudent_no);
		tfStudent_no.setColumns(10);
		
		label = new JLabel("이름");
		label.setBounds(22, 70, 57, 15);
		contentPane.add(label);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(123, 67, 116, 21);
		contentPane.add(tfName);
		
		label_1 = new JLabel("국어");
		label_1.setBounds(22, 111, 57, 15);
		contentPane.add(label_1);
		
		tfKor = new JTextField();
		tfKor.setColumns(10);
		tfKor.setBounds(123, 108, 116, 21);
		contentPane.add(tfKor);
		
		label_2 = new JLabel("영어");
		label_2.setBounds(22, 152, 57, 15);
		contentPane.add(label_2);
		
		tfEng = new JTextField();
		tfEng.setColumns(10);
		tfEng.setBounds(123, 149, 116, 21);
		contentPane.add(tfEng);
		
		label_3 = new JLabel("수학");
		label_3.setBounds(22, 197, 57, 15);
		contentPane.add(label_3);
		
		tfMat = new JTextField();
		tfMat.setColumns(10);
		tfMat.setBounds(123, 194, 116, 21);
		contentPane.add(tfMat);
		
		btnSave = new JButton("저장");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String student_no=tfStudent_no.getText();
				if(student_no.trim().equals("")) {
					JOptionPane.showMessageDialog(ScoreExam.this, "데이터를 입력하세요.");
					return;
				}
				input();
				int result=dao.insertScore(dto);
				if(result != 0) {
					JOptionPane.showMessageDialog(ScoreExam.this, "입력 완료");
					refreshList();
					tfClear();
				}else {
					JOptionPane.showMessageDialog(ScoreExam.this, "입력데이터를 확인 하시기 바랍니다.");
				}
			}
		});
		btnSave.setBounds(313, 31, 97, 23);
		contentPane.add(btnSave);
		
		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String student_no=tfStudent_no.getText();
				if(student_no.trim().equals("")) {
					JOptionPane.showMessageDialog(ScoreExam.this, "수정할 데이터를 선택하세요.");
					return;
				}
				input();
				int result=dao.updateScore(dto);
				if(result != 0) {
					JOptionPane.showMessageDialog(ScoreExam.this, "수정 완료");
					refreshList();
					tfClear();
				}
			}
		});
		btnUpdate.setBounds(313, 80, 97, 23);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result=0;
				String student_no=tfStudent_no.getText();
				if(student_no.trim().equals("")) {
					JOptionPane.showMessageDialog(ScoreExam.this, "삭제할 데이터를 선택하세요.");
					return;
				}
				result=JOptionPane.showConfirmDialog(ScoreExam.this, "데이터를 삭제 하시겠습니까?");
				if(result==JOptionPane.YES_OPTION)	{
					result=dao.deleteScore(student_no);
				} else return;
				if(result!=0) {
					JOptionPane.showMessageDialog(ScoreExam.this, "삭제 완료");
					refreshList();
					tfClear();
				}
			}
		});
		btnDelete.setBounds(313, 130, 97, 23);
		contentPane.add(btnDelete);
		
		JLabel lblNewLabel_1 = new JLabel("검색어를 입력하세요.");
		lblNewLabel_1.setBounds(22, 247, 131, 15);
		contentPane.add(lblNewLabel_1);
		
		tfSearch = new JTextField();
		tfSearch.setBounds(154, 244, 116, 21);
		contentPane.add(tfSearch);
		tfSearch.setColumns(10);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=tfSearch.getText();
				model=new DefaultTableModel(dao.searchScore(name), col);
				table.setModel(model);
			}
		});
		btnSearch.setBounds(313, 243, 97, 23);
		contentPane.add(btnSearch);
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
	
	void refreshList() {
		model=new DefaultTableModel(dao.listScore(), col);
		table.setModel(model);
	}
	
	void tfClear() {
		tfStudent_no.setText("");
		tfName.setText("");
		tfKor.setText("");
		tfEng.setText("");
		tfMat.setText("");
		tfStudent_no.requestFocus();
	}
}
