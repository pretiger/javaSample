package aboutGraphics;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JFrame;

public class StringLine extends JFrame {
	Container conn;
	public StringLine() {
		conn=getContentPane();
		setSize(300,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		conn.setBackground(Color.yellow);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.blue);
		g.drawLine(250,20,30,150);
		g.setColor(Color.red);
		g.drawString("Red String",10,50);
		g.setColor(Color.green);
		g.drawString("Green String",10, 80);
		g.setColor(Color.blue);
		g.drawString("Blue String",10, 150);
	}
	
	public static void main(String[] args) {
		new StringLine();
	}
}
