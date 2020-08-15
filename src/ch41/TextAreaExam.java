package ch41;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TextAreaExam extends JFrame {

	private JPanel contentPane;
	private JTextField tf;
	private JTextArea ta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextAreaExam frame = new TextAreaExam();
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
	public TextAreaExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		tf = new JTextField();
		tf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				putResult();
			}
		});
		panel.add(tf);
		tf.setColumns(10);
		
		JButton bt = new JButton("입력");
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				putResult();
			}
		});
		panel.add(bt);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		ta = new JTextArea();
		scrollPane.setViewportView(ta);
	}
	
	void putResult() {
		//텍스트필드에 입력한 내용
		String str=tf.getText();
		//텍스트에어리어에 덧붙임 "\n" 줄바꿈
		ta.append(str+"\n");
		//텍스트필드 입력값을 지움
		tf.setText("");
		//텍스트필드로 촛점을 돎김
		tf.requestFocus();
	}

}
