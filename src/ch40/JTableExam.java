package ch40;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTableExam extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JTableExam frame = new JTableExam();
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
	public JTableExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		String[] title = {"번호", "이름", "전화번호"};
		String[][] data = {
				{"1", "김철수", "02-201-2050"},
				{"2", "강강원", "031-203-2045"},
				{"3", "이하나", "02-201-2333"}
		};
		
		table = new JTable(data, title);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "\uAE40\uCCA0\uC218", "02-201-2050"},
				{"2", "\uAC15\uAC15\uC6D0", "031-203-2045"},
				{"3", "\uC774\uD558\uB098", "02-201-2333"},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"\uBC88\uD638", "\uC774\uB984", "\uC804\uD654\uBC88\uD638"
			}
		));
		scrollPane.setViewportView(table);
	}

}
