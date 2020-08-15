package ch03;

import java.awt.Graphics;

public class DrawCircle extends Point implements Draw {
	private int width, height;
	public DrawCircle() {
		this(0,0,0,0);
	}
	public DrawCircle(int a, int b, int w, int h) {
		super(a,b);
		width=w;
		height=h;
	}
	@Override
	public void paint(Graphics g) {
		g.drawOval(a, b, width, height);
	}

}
