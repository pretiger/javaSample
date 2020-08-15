package aboutBasicComponent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class CheckBoxExam extends JFrame {

	private JPanel contentPane;
	private JTextArea ta;

	/**
	 * Launch the application.
	 */
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
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JCheckBox ckJava = new JCheckBox("자바");
		ckJava.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				putRegister(e);
			}
		});
		panel.add(ckJava);
		
		JCheckBox ckC = new JCheckBox("C언어");
		ckC.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				putRegister(e);
			}
		});
		panel.add(ckC);
		
		JCheckBox ckDB = new JCheckBox("데이터베이스");
		ckDB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				putRegister(e);
			}
		});
		panel.add(ckDB);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		ta = new JTextArea();
		scrollPane.setViewportView(ta);
	}
	void putRegister(ItemEvent e){
		JCheckBox ck=(JCheckBox)e.getSource();
		if(e.getStateChange()==ItemEvent.SELECTED) {
			ta.append(ck.getText()+"를 신청하셨습니다.\n");
		} else {
			ta.append(ck.getText()+"를 취소하셨습니다.\n");
		}
	}

}
