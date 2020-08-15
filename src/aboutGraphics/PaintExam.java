package aboutGraphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class PaintExam extends JFrame {
	public PaintExam() {
		setSize(300,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
//		getContentPane().setBackground(Color.yellow);
		g.drawString("Hellow Java",10, 50);
		g.fillOval(100, 100, 40, 40);
		g.drawOval(100, 50, 40, 40);
		g.fillRect(200, 200, 40, 40);
		g.drawRect(200, 150, 40, 40);
	}
	public static void main(String[] args) {
		new PaintExam();
	}
}
