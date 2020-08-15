package ch41;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

public class TabExam extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabExam frame = new TabExam();
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
	public TabExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("tab1", null, panel, null);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("버튼1");
		btnNewButton.setBounds(12, 10, 97, 23);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("tab2", null, panel_1, null);
		panel_1.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("버튼2");
		btnNewButton_1.setBounds(43, 60, 97, 23);
		panel_1.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("tab3", null, panel_2, null);
		panel_2.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("버튼3");
		btnNewButton_2.setBounds(183, 90, 97, 23);
		panel_2.add(btnNewButton_2);
	}

}
