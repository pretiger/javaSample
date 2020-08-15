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
import javax.swing.JPanel;

public class BallCanvas2 extends JPanel {
	public BallCanvas2() {
		super(new BorderLayout());//If Super() space, then JPanel is BorderLayout.
		MoveBall canvas=new MoveBall();
		canvas.setSize(300, 300);
		add(canvas);
	}
	static void createAndShowUI() {
		JFrame frame=new JFrame("BallCanvas2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BallCanvas2 newContentPain=new BallCanvas2();
		newContentPain.setOpaque(true);
		frame.setContentPane(newContentPain);
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowUI();
			}
		});
	}
	class MoveBall extends Canvas implements Runnable, ComponentListener {
		int x,y,moveX=2,moveY=3,width,height,red,green,blue;
		Graphics bg;
		Image offScreen;
		Random random;
		
		MoveBall(){
			addComponentListener(this);
			Thread th=new Thread(this);
			random=new Random();
			th.start();
		}
		@Override
		public void update(Graphics g) {
			Dimension d=getSize();
			if(offScreen == null) {
				offScreen=createImage(d.width,d.height);
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
//			g.setColor(new Color(red,green,blue));
//			g.fillOval(x, y, 20, 20);
		}
		@Override
		public void componentResized(ComponentEvent e) {
			width=getWidth();
			height=getHeight();
			offScreen=createImage(width,height);
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
				if(x < 0 || x>(width-20)) {
					moveX = -moveX;
					setColor();
				}
				if(y < 0 || y>(height-20)) {
					moveY = -moveY;
					setColor();
				}
				x += moveX;
				y +=moveY;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
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
}
