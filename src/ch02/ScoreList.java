package ch02;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScoreList extends JFrame {

	private JPanel contentPane;
	private Vector col, data;
	private ScoreDAO dao;
	private ScoreDTO dto;
	private DefaultTableModel model;
	private int result;
	private JPanel panel;
	private JButton btnSave;
	private JButton btnEdit;
	private JScrollPane scrollPane;
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		btnSave = new JButton("추가");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoreSave form=new ScoreSave(ScoreList.this);
				form.setVisible(true);
			}
		});
		panel.add(btnSave);
		
		btnEdit = new JButton("수정/삭제");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx=table.getSelectedRow();
				if(idx==-1) {
					JOptionPane.showMessageDialog(null, "데이터를 선택 후 수정/삭제 버튼을 클릭하세요!");
					return;
				}
				String student_no=table.getValueAt(idx, 0)+"";
				String name=table.getValueAt(idx, 1)+"";
				int kor=Integer.parseInt(table.getValueAt(idx, 2)+"");
				int eng=Integer.parseInt(table.getValueAt(idx, 3)+"");
				int mat=Integer.parseInt(table.getValueAt(idx, 4)+"");
				dto=new ScoreDTO(student_no, name, kor, eng, mat);
				ScoreEdit form=new ScoreEdit(ScoreList.this, dto);
				form.setVisible(true);
			}
		});
		panel.add(btnEdit);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		refreshTable();
	}
	public void refreshTable() {
		model=new DefaultTableModel(dao.listScore(), col);
		table.setModel(model);
	}
}
