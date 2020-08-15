package java01;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ComboExam extends JFrame {

	private JPanel contentPane;
	private JTextArea ta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComboExam frame = new ComboExam();
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
	public ComboExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JComboBox cboDan = new JComboBox();
		cboDan.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int dan=Integer.parseInt((String)cboDan.getSelectedItem());
				//textarea 화면초기화
				ta.setText("");
				for(int i=1; i<10; i++) {
					ta.append(String.format("%d X %d = %2d\n", dan, i, dan*i));
				}
			}
		});
		cboDan.setModel(new DefaultComboBoxModel(new String[] {"", "2", "3", "4", "5", "6", "7", "8", "9"}));
		contentPane.add(cboDan, BorderLayout.NORTH);
		
		ta = new JTextArea();
		contentPane.add(ta, BorderLayout.CENTER);
	}

}
