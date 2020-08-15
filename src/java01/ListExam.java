package java01;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListExam extends JFrame {

	private JPanel contentPane;
	private JTextField tf;
	private JList list;
	//JList에 목록을 출력하기 위해서 model추가
	private DefaultListModel<String> model;

	/**
	 * Launch the application.
	 */
	//JLabel 문자열 출력		getText() 내용읽기
	//JTextField 한줄 입력	setText("문자열") 내용변경
	//JTextArea 여러줄 입력	append("문자열") 내용 추가(덧붙임)
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
				//텍스트필드에 입력한 내용을 모델에 추가(리스트가 갱신)
				model.addElement(tf.getText());
				//테스트필드 초기화
				tf.setText("");
				//입력커서 이동
				tf.requestFocus();
			}
		});
		panel.add(tf);
		tf.setColumns(10);
		
		JButton btnAdd = new JButton("추가");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//텍스트필드에 입력한 내용을 모델에 추가(리스트가 갱신)
				model.addElement(tf.getText());
				//테스트필드 초기화
				tf.setText("");
				//입력커서 이동
				tf.requestFocus();
			}
		});
		panel.add(btnAdd);
		
		JButton btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JList에서 선택된 index값
				int index=list.getSelectedIndex();
				//항목을 선택하지 않고 삭제하려고 살시 index -1 리턴
				//이때 확인 콘솔화면에서 System.out.println("인텍스값:",+index)로 확인
				if(index != -1) {
					model.remove(index);
				}else {
					JOptionPane.showMessageDialog(ListExam.this, "삭제할 내용을 선택하세요.");
				}
			}
		});
		panel.add(btnDelete);
		//JList에 추가할 model 작성
		model=new DefaultListModel<>();
		model.addElement("서울");
		model.addElement("부산");
		model.addElement("대전");
		model.addElement("인천");
		model.addElement("춘천");
		
		list = new JList<>(model);
		contentPane.add(list, BorderLayout.CENTER);
	}

}
