package ch40;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseCanvas extends Canvas implements MouseListener, KeyListener{
	private int x, y;
	private int width, height;
	private Image img;
	
	public MouseCanvas() {
		//클래스 파일이 위치한 곳의 그림파일
		img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("ico1.jpg"));
		//마우스의 동작감지
		addMouseListener(this);
		addKeyListener(this);
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
		//마우스 클릭시 x, y좌표 저장 후 다시 화면에 출력
		x=e.getX() - width/2;
		y=e.getY() - height/2;
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
		//화면 사이즈를 1000, 1000으로 설정했을 경우 화살표키 움직임 처리
		switch(e.getKeyCode()) { //키코드값에 따라 분기
		case KeyEvent.VK_UP:
			y=Math.max(0, y-20); break;
		case KeyEvent.VK_DOWN:
			y=Math.min(1000-height, y+5); break;
		case KeyEvent.VK_LEFT:
			x=Math.max(0, x-20); break;
		case KeyEvent.VK_RIGHT:
			x=Math.min(1000-width, x+5); break;
		}
		repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
}
