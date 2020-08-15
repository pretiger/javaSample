package ch53;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class ScoreList extends JFrame {
	//추가코드
	private ScoreDAO dao;
	private ScoreDTO dto;
	private Vector<String> col; //테이블의 제목행을 구하기 위한 벡터
	private DefaultTableModel model; //테이블에 데이터를 공급하는 객체
	
	private JPanel contentPane;
	private JTextField tfStudentNo;
	private JTextField tfName;
	private JTextField tfKor;
	private JTextField tfEng;
	private JTextField tfMat;
	private JTable table;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel label;
	private JTextField tfSearch;
	private JButton btnSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreList frame = new ScoreList();
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
	public ScoreList() { //생성자
		dao=new ScoreDAO();  //dao 객체 생성
		col=new Vector<>();  //테이블의 타이틀 행에 입력할 벡터 생성
		col.add("학번");
		col.add("이름");
		col.add("국어");
		col.add("영어");
		col.add("수학");
		col.add("총점");
		col.add("평균");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("학번");
		lblNewLabel.setBounds(12, 10, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setBounds(12, 35, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("국어");
		lblNewLabel_2.setBounds(12, 60, 57, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("영어");
		lblNewLabel_3.setBounds(12, 85, 57, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("수학");
		lblNewLabel_4.setBounds(12, 110, 57, 15);
		contentPane.add(lblNewLabel_4);
		
		tfStudentNo = new JTextField();
		tfStudentNo.setBounds(92, 7, 116, 21);
		contentPane.add(tfStudentNo);
		tfStudentNo.setColumns(10);
		
		tfName = new JTextField();
		tfName.setBounds(92, 32, 116, 21);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfKor = new JTextField();
		tfKor.setBounds(92, 57, 116, 21);
		contentPane.add(tfKor);
		tfKor.setColumns(10);
		
		tfEng = new JTextField();
		tfEng.setBounds(92, 82, 116, 21);
		contentPane.add(tfEng);
		tfEng.setColumns(10);
		
		tfMat = new JTextField();
		tfMat.setBounds(92, 107, 116, 21);
		contentPane.add(tfMat);
		tfMat.setColumns(10);
		
		btnSave = new JButton("저장");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input();
				int result=dao.insertScore(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(ScoreList.this, "저장되었습니다.");
					list();
					table.setModel(model);
					clear();
				}
				
			}
		});
		btnSave.setBounds(231, 6, 97, 23);
		contentPane.add(btnSave);
		
		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input();
				int result=dao.updateScore(dto);
				if(result == 1) {
					JOptionPane.showMessageDialog(ScoreList.this, "수정되었습니다.");
					list();
					table.setModel(model);
					clear();
				}
			}
		});
		btnUpdate.setBounds(231, 35, 97, 23);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String student_no=tfStudentNo.getText();
				if(student_no.trim().equals("")) {
					JOptionPane.showMessageDialog(ScoreList.this, "삭제할 내용을 선택하세요.");
					return;
				}
				int result=0;
				int response=JOptionPane.showConfirmDialog(ScoreList.this, "삭제하시겠습니까?");
				if(response == JOptionPane.YES_OPTION) {
					result=dao.deleteScore(student_no);
				}
				if(result == 1) {
					JOptionPane.showMessageDialog(ScoreList.this, "삭제되었습니다.");
					list();
					table.setModel(model);
					clear();
				}
			}
		});
		btnDelete.setBounds(231, 60, 97, 23);
		contentPane.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 184, 410, 222);
		contentPane.add(scrollPane);
		
		list(); //테이블 모델의 내용 갱신
		
		table = new JTable(model); //table에 model을 추가
		scrollPane.setViewportView(table);
		
		label = new JLabel("이름을 입력하세요");
		label.setBounds(12, 141, 114, 15);
		contentPane.add(label);
		
		tfSearch = new JTextField();
		tfSearch.setColumns(10);
		tfSearch.setBounds(138, 138, 116, 21);
		contentPane.add(tfSearch);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=tfSearch.getText();
				//JTable에 데이터를 공급하는 모델 내용 변경
				model=new DefaultTableModel(dao.searchScore(name), col);
				//JTable의 내용이 변경됨
				table.setModel(model);
			}
		});
		btnSearch.setBounds(280, 137, 97, 23);
		contentPane.add(btnSearch);
		
		//테이블에 마우스클릭 이벤트 추가
		table.addMouseListener(new MouseAdapter() {
			//마우스를 클릭할대 호출
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx=table.getSelectedRow(); //현재 클릭된 행의 인덱스
				//getValueAt(행인덱스, 열인덱스)
				tfStudentNo.setText(table.getValueAt(idx, 0)+"");
				tfName.setText(table.getValueAt(idx, 1)+"");
				tfKor.setText(table.getValueAt(idx, 2)+"");
				tfEng.setText(table.getValueAt(idx, 3)+"");
				tfMat.setText(table.getValueAt(idx, 4)+"");
			}
		});
	}
	
	public void list() {
		//new DefaultTableModel(행, 열)
		model=new DefaultTableModel(dao.listScore(), col);
	}
	
	public void input() {
		String student_no=tfStudentNo.getText();
		String name=tfName.getText();
		int kor=Integer.parseInt(tfKor.getText());
		int eng=Integer.parseInt(tfEng.getText());
		int mat=Integer.parseInt(tfMat.getText());
		dto=new ScoreDTO(student_no, name, kor, eng, mat);
	}
	
	public void clear() {
		tfStudentNo.setText("");
		tfName.setText("");
		tfKor.setText("");
		tfEng.setText("");
		tfMat.setText("");
		tfStudentNo.requestFocus();
	}
	
}
