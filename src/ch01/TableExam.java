package ch01;

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
		
		String[] cal= {"사원번호","이름","입사일자","이메일"};
		String[][] data= {{"1","kim","2010-01-01","pre@daum.net"},
						  {"2","park","2011-02-02","ars@daum.net"},
						  {"3","kang","2012-03-03","ksr@daum.net"}
		};
		
		
		table = new JTable(data,cal);
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{"1", "\uAC15\uAE30\uB3D9", "2001-01-10", "pre@daum.net"},
//				{"2", "\uAE40\uC778\uC218", "2010-01-02", "ars@daum.net"},
//				{"3", "\uC774\uBBF8\uB77C", "2011-03-03", "psc@daum.net"},
//			},
//			new String[] {
//				"\uC0AC\uC6D0\uBC88\uD638", "\uC774\uB984", "\uC785\uC0AC\uC77C\uC790", "\uC774\uBA54\uC77C"
//			}
//		));
		scrollPane.setViewportView(table);
	}

}
