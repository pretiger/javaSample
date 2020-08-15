package ch01;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class WindExam extends Frame{
	public WindExam(){
		setSize(300, 300);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "종료합니다.");
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		new WindExam();
	}
}
