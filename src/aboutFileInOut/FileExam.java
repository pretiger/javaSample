package aboutFileInOut;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FileExam extends JFrame {

	private JPanel contentPane;
	private JTextField tfFile;
	private JTextArea textArea;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileExam frame = new FileExam();
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
	public FileExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		tfFile = new JTextField();
		panel.add(tfFile);
		tfFile.setColumns(10);
		
		btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file=new File(tfFile.getText());
				if(!file.exists()) {
					try {
						file.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				String str="파일이름:"+file.getName()+"\n파일크기:"+file.length()+"\n상위폴더:"+file.getParent();
				textArea.setText(str);
			}
		});
		panel.add(btnNewButton);
		
		textArea = new JTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
	}

}
