package ch50;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;

public class FileRead extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileRead frame = new FileRead();
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
	public FileRead() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("파일이름");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(25);
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//텍스트 필드에 입력한 파일 경로
				String file=textField.getText();
				String str="";
				BufferedReader reader=null;
				try {
					//new BufferedReader(InputStreamReader객체(InputStream객체(file객체)))
					//new BufferedReader(InputStreamReader객체(FileInputStream객체(파일이름)))
					reader=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
					//reader=new BufferedReader(new InputStreamReader(new FileInputStream(new File(file))));
					textArea.setText("");
					while(true) {
						try {
							str=reader.readLine();//한 줄을 읽음
							if(str==null)	break;//더 이상 읽을 내용이 없으면 종료
							textArea.append(str+"\n");//화면에 출력
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}

}
