package ch41;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JApplet;
//마우스 이벤트 처리 MouseListener => MouseEvent
public class KeyMouseExam extends JApplet implements MouseListener, MouseMotionListener, KeyListener {

	private int x, y;
	private int width, height;
	private Image img;
	//화면 출력전 초기화
	@Override
	public void init() {
		setSize(300,300);
		//이미지 로딩
		img=Toolkit.getDefaultToolkit().getImage(getClass().getResource("duke1.jpg"));
		//마우스 이벤트 연결
		addMouseListener(this);
		addMouseMotionListener(this);
		//키이벤트 연결
		addKeyListener(this);
		//키 입력을 받을 수 있도록 설정
		setFocusable(true);
		//현재 화면에 입력 포커스 설정
		requestFocus();
	}
	//화면 출력 코드
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//이미지를 화면에 출력
		g.drawImage(img,x,y,null);

	}
	@Override
	public void mouseClicked(MouseEvent e) {
		//이미지의 가로 세로 계산
		width = img.getWidth(null);
		height = img.getHeight(null);
		//클릭한 x, y 좌표 저장
		x=e.getX();
		y=e.getY();
		//그래픽 수정 요청 => paint()가 호출됨
		repaint();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {//키코드값에 따라 분기
		case KeyEvent.VK_UP:
			y=Math.max(0, y-5); break;
		case KeyEvent.VK_DOWN:
			y=Math.min(300-height, y+5); break;
		case KeyEvent.VK_LEFT:
			x=Math.max(0, x-5); break;
		case KeyEvent.VK_RIGHT:
			x=Math.min(300-width, x+5); break;
		}
		repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println(
				"mouse drag... x:"+e.getX()+",y"+e.getY());
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println(
				"mouse move... x:"+e.getX()+",y"+e.getY());
	}
}
