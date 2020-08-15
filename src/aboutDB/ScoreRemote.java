package aboutDB;

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
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;

public class ScoreRemote extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnAdd;
	private JButton btnEdit;
	private ScoreDTO dto;
	private ScoreDAO dao;
	private Vector data, col;
	private DefaultTableModel model;
	private JButton btnPrint;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreRemote frame = new ScoreRemote();
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
	public ScoreRemote() {
		col=new Vector();
		col.add("학번");
		col.add("이름");
		col.add("국어");
		col.add("영어");
		col.add("수학");
		col.add("총점");
		col.add("평균");
		dao=new ScoreDAO();
		dto=new ScoreDTO();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 77, 410, 185);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		refreshList();
		
		btnAdd = new JButton("점수추가");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoreSave form=new ScoreSave(ScoreRemote.this);
				form.setVisible(true);
				form.setLocation(400, 400);
			}
		});
		btnAdd.setBounds(83, 10, 97, 23);
		contentPane.add(btnAdd);
		
		btnEdit = new JButton("수정/삭제");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx=table.getSelectedRow();
				if(idx==-1) {
					JOptionPane.showMessageDialog(ScoreRemote.this, "편집할 데이터를 선택하세요.");
					return;
				}
				String student_no=table.getValueAt(idx, 0)+"";
				String name=table.getValueAt(idx, 1)+"";
				int kor=Integer.parseInt(table.getValueAt(idx, 2)+"");
				int eng=Integer.parseInt(table.getValueAt(idx, 3)+"");
				int mat=Integer.parseInt(table.getValueAt(idx, 4)+"");
				int tot=0;
				double avg=0.0;
				dto=new ScoreDTO(student_no,name,kor,eng,mat,tot,avg);
				ScoreEdit form=new ScoreEdit(ScoreRemote.this, dto);
				form.setVisible(true);
				form.setLocation(400,400);
			}
		});
		btnEdit.setBounds(226, 10, 97, 23);
		contentPane.add(btnEdit);
		
		btnPrint = new JButton("프린트");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        MessageFormat header = new MessageFormat("Page {0,number,integer}");
		        try {
		            table.print(JTable.PrintMode.FIT_WIDTH, header, null);
		        } catch (java.awt.print.PrinterException e1) {
		            System.err.format("Cannot print %s%n", e1.getMessage());
		        }
			}
		});
		btnPrint.setBounds(169, 282, 97, 23);
		contentPane.add(btnPrint);
	}
	
	void refreshList() {
		model=new DefaultTableModel(dao.listScore(), col);
		table.setModel(model);
	}
}
