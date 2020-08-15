package ch41;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableExam extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableExam frame = new TableExam();
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
	public TableExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		//테이블의 헤더 - 1차원 배열로 작성
		String[] title = {"번호", "이름","전화번호"};
		//테이블의 데이터 - 2차원 배열로 작성
		String[][] data = {
				{"1","김인호","02-231-8889"},
				{"2","장창호","031-239-8223"},
				{"3","이하나","02-239-3342"}
		};
		//new JTable(데이터, 타이틀)
		table = new JTable(data, title);
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{"\uAE40\uC778\uD638", "02-203-2222", "jskim@daum.net"},
//				{"\uAC15\uCC3D\uD638", "032-291-3488", "csjeong@hanmail.com"},
//				{"\uC774\uD558\uB098", "02-231-3399", null},
//			},
//			new String[] {
//				"\uC774\uB984", "\uC804\uD654\uBC88\uD638", "\uC774\uBA54\uC77C"
//			}
//		));
		table.getColumnModel().getColumn(1).setPreferredWidth(102);
		table.getColumnModel().getColumn(2).setPreferredWidth(144);
		scrollPane.setViewportView(table);
	}

}
