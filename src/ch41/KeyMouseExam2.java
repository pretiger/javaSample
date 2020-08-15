package ch41;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class KeyMouseExam2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KeyMouseExam2 frame = new KeyMouseExam2();
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
	public KeyMouseExam2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		KeyCanvas canvas = new KeyCanvas();
		contentPane.add(canvas, BorderLayout.CENTER);
	}

}

class KeyCanvas extends Canvas implements MouseListener,KeyListener {
	private Image img;
	private int x,y,width,height;
	
	KeyCanvas(){
		addMouseListener(this);
		addKeyListener(this);
		img=Toolkit.getDefaultToolkit().getImage(getClass().getResource("duke1.jpg"));
		setFocusable(true);
		requestFocus();
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, x, y, null);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		width=img.getWidth(null);
		height=img.getHeight(null);
		x=e.getX()-(width/2);
		y=e.getY()-(height/2);

		repaint();
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		Dimension d=getSize();
		switch(e.getKeyCode()) {//키코드값에 따라 분기
		case KeyEvent.VK_UP:
			y=Math.max(0, y-5); break;
		case KeyEvent.VK_DOWN:
			y=Math.min(d.height-height, y+5); break;
		case KeyEvent.VK_LEFT:
			x=Math.max(0, x-5); break;
		case KeyEvent.VK_RIGHT:
			x=Math.min(d.width-width, x+5); break;
		}

		repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
}