package ch60;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ch53.ScoreDAO;
import ch53.ScoreDTO;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScoreList extends JFrame {

	private JPanel contentPane;
	
	private ScoreDAO dao;
	private Vector data, col;
	private JTable table;

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
	public ScoreList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdd = new JButton("점수추가");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//프레임 생성
				ScoreSave form=new ScoreSave(ScoreList.this);
				//프레임을 화면에 표시
				form.setVisible(true);
				//프레임을 출력할 좌표 지정
				form.setLocation(400,200);
			}
		});
		btnAdd.setBounds(12, 26, 97, 23);
		contentPane.add(btnAdd);
		
		JButton btnEdit = new JButton("수정 / 삭제");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//선택한 레코드를 dto로 저장
				int idx=table.getSelectedRow();
				if(idx == -1) {
					JOptionPane.showMessageDialog(ScoreList.this, "편집할 행을 선택하세요!");
					return;//method종료
				}
				//String.valueOf(값) => String으로 변환, 이것을 쓰거나 밑에 부분으로 사용가능
				//테이블.getValueAt(행인덱스, 열일덱스) 테이블의 셀 값
				String student_no=table.getValueAt(idx, 0)+"";
				String name=String.valueOf(table.getValueAt(idx, 1));
				
				int kor=Integer.parseInt(table.getValueAt(idx, 2)+"");
				int eng=Integer.parseInt(table.getValueAt(idx, 3)+"");
				int mat=Integer.parseInt(table.getValueAt(idx, 4)+"");
				ScoreDTO dto=new ScoreDTO(student_no, name, kor, eng, mat);
				//ScoreEdit화면을 프레임에 표시
				ScoreEdit form=new ScoreEdit(ScoreList.this, dto);
				form.setVisible(true);//프레임을 화면에 표시
				form.setLocation(400,200);//프레임의 좌표 지정
			}
		});
		btnEdit.setBounds(135, 26, 97, 23);
		contentPane.add(btnEdit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 68, 396, 184);
		contentPane.add(scrollPane);
		
		//dao 생성
		dao=new ScoreDAO();
		//테이블의 타이틀 행에 입력할 벡터 생성
		col=new Vector();  
		col.add("학번");
		col.add("이름");
		col.add("국어");
		col.add("영어");
		col.add("수학");
		col.add("총점");
		col.add("평균");
		table = new JTable();
		scrollPane.setViewportView(table);
		//JTable을 갱신하는 method호출
		refreshTable();
		
	}//생성자
	
	public void refreshTable() {
		DefaultTableModel model=new DefaultTableModel(dao.listScore(), col);
		table.setModel(model);
	}

}//클래스
