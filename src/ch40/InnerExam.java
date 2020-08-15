package ch40;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//F3 : 소스코드 보기
//F4 : 계층구조 보기
public class InnerExam extends Frame {
	public InnerExam() {
		super("내부 클래스 테스트");
		setSize(300, 400);
		setVisible(true);
	}
	//Ctrl+Shift+o 자동 import
	public static void main(String[] args) {
		InnerExam e = new InnerExam();
		//정확한 대소문자 구분, windowClosing
		e.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
}
