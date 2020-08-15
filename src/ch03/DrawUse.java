package ch03;

import java.applet.Applet;
import java.awt.Graphics;

public class DrawUse extends Applet {
	Draw shape1;
	Draw shape2;
	public DrawUse() {
		shape1=new DrawCircle(30,30,60,80);
		shape2=new DrawCircle(10,20,50,100);
	}
	public void paint(Graphics g) {
		g.drawString("자바그래픽", 100, 60);
		shape1.paint(g);
		shape2.paint(g);
	}
}
