package ch02;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class ScoreList2 extends JFrame {

	private JPanel contentPane;
	private JTextField tfStudent_no;
	private JTextField tfName;
	private JTextField tfKor;
	private JTextField tfEng;
	private JTextField tfMat;
	private JButton btnSave;
	private JButton ttnUpdate;
	private JButton btnDelete;
	private Vector col;
	private ScoreDAO dao;
	private ScoreDTO dto;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField tfSearch;
	private JButton btnSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreList2 frame = new ScoreList2();
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
	public ScoreList2() {
		col=new Vector();
		col.add("학번");
		col.add("이름");
		col.add("국어");
		col.add("영어");
		col.add("수학");
		col.add("총정");
		col.add("평균");
		
		dao=new ScoreDAO();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("학번");
		lblNewLabel.setBounds(22, 20, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("이름");
		label.setBounds(22, 45, 57, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("국어");
		label_1.setBounds(22, 70, 57, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("영어");
		label_2.setBounds(22, 95, 57, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("수학");
		label_3.setBounds(22, 120, 57, 15);
		contentPane.add(label_3);
		
		tfStudent_no = new JTextField();
		tfStudent_no.setBounds(95, 17, 116, 21);
		contentPane.add(tfStudent_no);
		tfStudent_no.setColumns(10);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(95, 42, 116, 21);
		contentPane.add(tfName);
		
		tfKor = new JTextField();
		tfKor.setColumns(10);
		tfKor.setBounds(95, 67, 116, 21);
		contentPane.add(tfKor);
		
		tfEng = new JTextField();
		tfEng.setColumns(10);
		tfEng.setBounds(95, 92, 116, 21);
		contentPane.add(tfEng);
		
		tfMat = new JTextField();
		tfMat.setColumns(10);
		tfMat.setBounds(95, 117, 116, 21);
		contentPane.add(tfMat);
		
		btnSave = new JButton("저장");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
				int result=dao.insertScore(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(ScoreList2.this, "저장 완료");
					list();
					table.setModel(model);
					clear();
				}else JOptionPane.showMessageDialog(ScoreList2.this, "저장 실패");
			}
		});
		btnSave.setBounds(254, 16, 97, 23);
		contentPane.add(btnSave);
		
		ttnUpdate = new JButton("수정");
		ttnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
				int result=dao.updateScore(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(ScoreList2.this, "수정 완료");
					list();
					table.setModel(model);
					clear();
				}else JOptionPane.showMessageDialog(ScoreList2.this, "수정 실패");
			}
		});
		ttnUpdate.setBounds(254, 45, 97, 23);
		contentPane.add(ttnUpdate);
		
		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
				int result=dao.deleteScore(dto.getStudent_no());
				if(result==1) {
					JOptionPane.showMessageDialog(ScoreList2.this, "삭제 완료");
					list();
					table.setModel(model);
					clear();
				}else JOptionPane.showMessageDialog(ScoreList2.this, "삭제 실패");
			}
		});
		btnDelete.setBounds(254, 78, 97, 23);
		contentPane.add(btnDelete);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 197, 400, 198);
		contentPane.add(scrollPane);
		
		list();
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JLabel label_4 = new JLabel("이름을 입력하세요");
		label_4.setBounds(22, 155, 116, 15);
		contentPane.add(label_4);
		
		tfSearch = new JTextField();
		tfSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		tfSearch.setColumns(10);
		tfSearch.setBounds(135, 148, 116, 21);
		contentPane.add(tfSearch);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnSearch.setBounds(275, 147, 97, 23);
		contentPane.add(btnSearch);
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
	}
	
	public void list() {
		model=new DefaultTableModel(dao.listScore(), col);
	}
	public void search() {
		model=new DefaultTableModel(dao.searchScore(tfSearch.getText()), col);
		table.setModel(model);
		clear();
	}
	public void insert() {
		String student_no=tfStudent_no.getText();
		String name=tfName.getText();
		int kor=Integer.parseInt(tfKor.getText());
		int eng=Integer.parseInt(tfEng.getText());
		int mat=Integer.parseInt(tfMat.getText());
		dto=new ScoreDTO(student_no, name, kor, eng, mat);
	}
	public void delete() {
		dto=new ScoreDTO();
		dto.setStudent_no(tfStudent_no.getText());
	}
	public void update() {
		String student_no=tfStudent_no.getText();
		String name=tfName.getText();
		int kor=Integer.parseInt(tfKor.getText());
		int eng=Integer.parseInt(tfEng.getText());
		int mat=Integer.parseInt(tfMat.getText());
		dto=new ScoreDTO(student_no, name, kor, eng, mat);
	}
	public void clear() {
		tfStudent_no.setText("");
		tfName.setText("");
		tfKor.setText("");
		tfEng.setText("");
		tfMat.setText("");
		tfStudent_no.requestFocus();
	}
}
