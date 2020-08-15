package aboutDB;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class ScoreRemakeMain extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnSave;
	private JButton btnUpdate;
	private ScoreDTO dto;
	private ScoreRemakeDAO dao,model;
	private Vector<Object> data,col;
	private JButton btnDelete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreRemakeMain frame = new ScoreRemakeMain();
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
	public ScoreRemakeMain() {
		dao=new ScoreRemakeDAO();
		col=new Vector<>();
		col.add("학번");
		col.add("이름");
		col.add("국어");
		col.add("영어");
		col.add("수학");
		col.add("총점");
		col.add("평균");
		data=dao.listScore();
		model=new ScoreRemakeDAO(data, col);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		btnSave = new JButton("저장");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoreRemakeSave form=new ScoreRemakeSave(ScoreRemakeMain.this);
				form.setVisible(true);
				form.setLocation(400, 400);
			}
		});
		panel.add(btnSave);
		
		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx=0;
				int result=0;
				
				idx=table.getSelectedRow();
				if(idx == -1) {
					JOptionPane.showMessageDialog(ScoreRemakeMain.this, "수정할 데이터를 선택하세요!");
					return;
				}
				String student_no=table.getValueAt(idx, 0)+"";
				String name=table.getValueAt(idx, 1)+"";
				int kor=Integer.parseInt(table.getValueAt(idx, 2)+"");
				int eng=Integer.parseInt(table.getValueAt(idx, 3)+"");
				int mat=Integer.parseInt(table.getValueAt(idx, 4)+"");
				int tot=kor+eng+mat;
				double avg=tot/3.0;
				dto=new ScoreDTO(student_no,name,kor,eng,mat,tot,avg);
				result=dao.updateScore(dto);
				if(result != 0) {
					JOptionPane.showMessageDialog(ScoreRemakeMain.this,"수정 완료");
					refreshTable();
				}
			}
		});
		panel.add(btnUpdate);
		
		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx=0;
				int result=0;
				String student_no=null;
				
				idx=table.getSelectedRow();
				if(idx == -1) {
					JOptionPane.showMessageDialog(ScoreRemakeMain.this, "삭제할 데이터를 선택하세요!");
					return;
				}
				student_no=table.getValueAt(idx, 0)+"";
				idx=JOptionPane.showConfirmDialog(ScoreRemakeMain.this, "데이터를 삭제할지 다시 한번 확인하세요. 삭제하시겠습니까?");
				if(idx == JOptionPane.YES_OPTION) {
					result=dao.deleteScore(student_no);
					if(result != 0) {
						JOptionPane.showMessageDialog(ScoreRemakeMain.this,"삭제 완료");
						refreshTable();
					}
				}
			}
		});
		panel.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(model);
		scrollPane.setViewportView(table);
	}
	
	void refreshTable() {
		data=dao.listScore();
		model=new ScoreRemakeDAO(data, col);
		table.setModel(model);
	}
}