package aboutFileInOut;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DirectoryExam extends JFrame {

	private JPanel contentPane;
	private JTextField tfDir;
	private JTextArea ta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DirectoryExam frame = new DirectoryExam();
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
	public DirectoryExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("디렉토리");
		panel.add(lblNewLabel);
		
		tfDir = new JTextField();
		panel.add(tfDir);
		tfDir.setColumns(10);
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File directory=new File(tfDir.getText());
				String[] list=directory.list();
				ta.setText("");
				for(String str : list) {
					File f=new File(directory, str);
					String strList=f.isDirectory() ? "디렉토리" : "파일";
//					ta.append(strList+" : "+f.getName()+"\n");
					ta.append(strList+" : "+str+"\n");
				}
			}
		});
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		ta = new JTextArea();
		scrollPane.setViewportView(ta);
	}

}
