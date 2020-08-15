package aboutBasicComponent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListExam extends JFrame {

	private JPanel contentPane;
	private JTextField tf;
	private JButton btnInsert;
	private JButton btnDelete;
	private JList<String> list;
	private DefaultListModel<String> model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListExam frame = new ListExam();
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
	public ListExam() {
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
				model.addElement(tf.getText());
				tf.setText("");
				tf.requestFocus();
			}
		});
		panel.add(tf);
		tf.setColumns(10);
		
		btnInsert = new JButton("추가");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.addElement(tf.getText());
				tf.setText("");
				tf.requestFocus();
			}
		});
		panel.add(btnInsert);
		
		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx=0;
				idx=list.getSelectedIndex();
				if(idx != -1) {
					model.remove(idx);
				} else {
					JOptionPane.showMessageDialog(ListExam.this, "삭제할 행을 선택하세요!");
				}
			}
		});
		panel.add(btnDelete);
		
		model=new DefaultListModel<>();
		model.addElement("서울");
		model.addElement("대구");
		model.addElement("광주");
		model.addElement("부산");
		model.addElement("제주");
		
		list = new JList<>(model);
		contentPane.add(list, BorderLayout.CENTER);
	}

}
