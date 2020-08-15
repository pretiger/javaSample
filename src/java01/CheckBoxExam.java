package java01;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckBoxExam extends JFrame {

	private JPanel contentPane;
	private JTextArea ta;

	/**
	 * Launch the application.
	 */
	//JButton		ActionListener	-> ActionEvent
	//JCheckBox
	//JRadioButton	ItemListener	-> ItemEvent
	//JComboBox 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckBoxExam frame = new CheckBoxExam();
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
	public CheckBoxExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnExit = new JButton("종료");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(btnExit, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JCheckBox ckJava = new JCheckBox("자바");
		ckJava.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				putResult(e);
			}
		});
		panel.add(ckJava);
		
		JCheckBox ckC = new JCheckBox("C언어");
		ckC.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				putResult(e);
			}
		});
		panel.add(ckC);
		
		JCheckBox ckIo = new JCheckBox("인터넷사물함");
		ckIo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				putResult(e);
			}
		});
		panel.add(ckIo);
		
		JCheckBox ckDB = new JCheckBox("데이터베이스");
		ckDB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				putResult(e);
			}
		});
		panel.add(ckDB);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		ta = new JTextArea();
		scrollPane.setViewportView(ta);
	}
	
	void putResult(ItemEvent e) {
		JCheckBox ck=(JCheckBox)e.getSource();
		if(e.getStateChange()==ItemEvent.SELECTED) {
			ta.append(ck.getText()+"를 신청했습니다.\n");
		}else if(e.getStateChange()==ItemEvent.DESELECTED){
			ta.append(ck.getText()+"를 취소했습니다.\n");
		}
	}

}
