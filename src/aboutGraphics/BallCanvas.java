package aboutGraphics;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Random;

import javax.swing.JFrame;

public class BallCanvas extends JFrame {
	public BallCanvas() {
		setSize(300,300);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Ball canvas=new Ball();
		add(canvas);
		setVisible(true);
	}
	public static void main(String[] args) {
		new BallCanvas();
	}
}

class Ball extends Canvas implements Runnable, ComponentListener {
	int x,y,moveX=2,moveY=3,width,height,red,green,blue;
	Random random;
	Image offScreen;
	Graphics bg;
	
	public Ball() {
		addComponentListener(this);
		random=new Random();
		Thread th=new Thread(this);
		th.start();
	}
	
	@Override
	public void update(Graphics g) {
		Dimension d=getSize();
		if(offScreen==null) {
			offScreen=createImage(d.width, d.height);
			bg=offScreen.getGraphics();
		}
		bg.setColor(getBackground());
		bg.fillRect(0, 0, d.width, d.height);
		bg.setColor(new Color(red,green,blue));
		bg.fillOval(x, y, 20, 20);
		paint(g);
	}
	
	@Override
	public void paint(Graphics g) {
		if(offScreen != null) {
			g.drawImage(offScreen, 0, 0, null);
		}
//		g.setColor(new Color(red,green,blue));
//		g.fillOval(x, y, 20, 20);
	}

	@Override
	public void componentResized(ComponentEvent e) {
		width=getWidth();
		height=getHeight();
		offScreen=createImage(width, height);
		bg=offScreen.getGraphics();
	}
	@Override
	public void componentMoved(ComponentEvent e) {
	}
	@Override
	public void componentShown(ComponentEvent e) {
	}
	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void run() {
		width=getWidth();
		height=getHeight();
		while(true) {
			if(x<0 || x>(width-20)) {
				moveX = -moveX;
				setColor();
			}
			if(y<0 || y>(height-20)) {
				moveY = -moveY;
				setColor();
			}
			x += moveX;
			y += moveY;
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
	void setColor() {
		red=random.nextInt(256);
		green=random.nextInt(256);
		blue=random.nextInt(256);
	}
	
}