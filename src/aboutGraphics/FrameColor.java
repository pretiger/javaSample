package aboutGraphics;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

public class FrameColor extends JFrame {
	
	public FrameColor() throws InterruptedException {
		setVisible(true);
		Container con=getContentPane();
		for(int i=0;i<256;i++) {
			setSize(i*2, i);
			setLocation(i*2, i);
			con.setBackground(new Color(0,i,0));
			Thread.sleep(10);
		}
		for(int i=0;i<256;i++) {
			setSize(i*2, i);
			setLocation(i*2, i);
			con.setBackground(new Color(i, 255, 0));
			Thread.sleep(10);
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) throws InterruptedException {
		new FrameColor();
	}
}
