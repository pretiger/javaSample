package ch04;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FileInfoExam extends JFrame {

	private JPanel contentPane;
	private JTextField tfFile;
	private JTextArea ta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileInfoExam frame = new FileInfoExam();
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
	public FileInfoExam() {
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
		
		JButton btnSelect = new JButton("파일선택");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				File file=new File(tfFile.getText());
//				if(!file.exists()) {
//					try {
//						file.createNewFile();
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//				}
////				String str="파일이름:"+file.getName()+"\n"
////						+"파일크기:"+file.length()+"\n"
////						+"상위디렉토리:"+file.getParent();
////				ta.setText(str);
//				ta.append("파일이름:"+file.getName()+"\n");
//				ta.append("파일크기:"+file.length()+"\n");
//				ta.append("상위디렉토리:"+file.getParent());
				JFileChooser fc=new JFileChooser();
				int result=fc.showOpenDialog(FileInfoExam.this);
				if(result==JFileChooser.APPROVE_OPTION) {
					File file=fc.getSelectedFile();
					String filePath=fc.getSelectedFile().getAbsolutePath();
					tfFile.setText(filePath);
					try(BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
						while(true) {
							if(br.readLine()==null) break;
							String str=br.readLine();
							ta.append(str+"\n");
						}
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel.add(btnSelect);
		
		ta = new JTextArea();
		contentPane.add(ta, BorderLayout.CENTER);
	}

}
