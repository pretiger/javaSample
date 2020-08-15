package aboutGraphics;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JFrame;

public class FontExam extends JFrame {
	Font font;
	FontMetrics fm;
	int x=100,y=100;
	String message;
	Dimension dim;
	
	public FontExam() {
		setSize(300,300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		message="그래픽 테스트";
		font=new Font("굴림", Font.ITALIC, 30);
		fm=getFontMetrics(font);
		dim=getSize();
		x=(dim.width/2)-(fm.stringWidth(message)/2);
		y=(dim.height/2)-(fm.getDescent()/2);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setFont(font);
		g.drawString(message, x, y);
	}
	
	public static void main(String[] args) {
		new FontExam();
	}
}
