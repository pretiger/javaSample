package aboutGraphics;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MoveBallDBuffering extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MoveBallDBuffering frame = new MoveBallDBuffering();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MoveBallDBuffering() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		BallDBuffering canvas = new BallDBuffering();
		contentPane.add(canvas, BorderLayout.CENTER);
	}

}

class BallDBuffering extends Canvas implements Runnable, ComponentListener {
	int x,y,moveX=2,moveY=3,width,height,red,green,blue;
	Random random;
	Image offScreen;
	Graphics bg;
	
	BallDBuffering() {
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
		bg.fillRect(0,0, d.width, d.height);
		bg.setColor(new Color(red,green,blue));
		bg.fillOval(x, y, 20, 20);
		paint(g);
	}
	@Override
	public void paint(Graphics g) {
		if(offScreen!=null) {
			g.drawImage(offScreen, 0, 0, null);
		}
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
				Thread.sleep(30);
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