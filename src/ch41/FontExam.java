package ch41;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class FontExam extends Applet {
	private Font font;
	private FontMetrics fm;
	private int x=0, y=0;
	private String message;
	private Dimension dim;
	
	@Override
	public void init() {
		message = "그래픽 테스트";
		//폰트설정 new Font("폰트이름", 옵션, 사이즈)
		font=new Font("굴림", Font.BOLD, 30);
		setSize(250, 250);
		fm=getFontMetrics(font);
		dim=getSize();
//		x=dim.width / 2;
//		y=dim.height /2;
		//stringWidth(문자열) 문자열의 가로길이
		x=(dim.width/2) - (fm.stringWidth(message)/2);
		//getDescent 문자열으 세로길이
		y=(dim.height/2) - (fm.getDescent()/2);
	}
	//화면 출력 처리
	@Override
	public void paint(Graphics g) {
		g.setFont(font);
		//그래픽 화면에 문자열 출력
		g.drawString(message, x, y);
	}
	public static void main(String[] args) {
		new FontExam();
	}
}
