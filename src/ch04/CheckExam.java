package ch04;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckExam extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextArea ta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckExam frame = new CheckExam();
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
	public CheckExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JCheckBox ckJava = new JCheckBox("Java");
		ckJava.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				putSelect(e);
			}
		});
		buttonGroup.add(ckJava);
		panel.add(ckJava);
		
		JCheckBox ckJSP = new JCheckBox("JSP");
		ckJSP.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				putSelect(e);
			}
		});
		buttonGroup.add(ckJSP);
		panel.add(ckJSP);
		
		JCheckBox ckDB = new JCheckBox("DB");
		ckDB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				putSelect(e);
			}
		});
		buttonGroup.add(ckDB);
		panel.add(ckDB);
		
		JCheckBox ckInternet = new JCheckBox("Internet");
		ckInternet.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				putSelect(e);
			}
		});
		buttonGroup.add(ckInternet);
		panel.add(ckInternet);
		
		JButton btnExit = new JButton("종료");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(btnExit, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		ta = new JTextArea();
		scrollPane.setViewportView(ta);
	}
	void putSelect(ItemEvent e){
		JCheckBox ck=(JCheckBox)e.getSource();
		if(e.getStateChange()==ItemEvent.SELECTED) {
			ta.append(ck.getText()+"을 선택하셨습니다.\n");
		}else {
			ta.append(ck.getText()+"을 해지하셨습니다.\n");
		}
		
	}
}
