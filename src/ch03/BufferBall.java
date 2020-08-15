package ch03;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
//더블 버퍼링(duoble buffering)
public class BufferBall extends Applet implements Runnable {
	private int x, y;//원의 좌표
	private int moveX=2, moveY=3;//x, y축의 이동거리
	private int width, heigh;//화면 가로 세로 사이즈
	//백그라운드 이미지에 그래픽처리를 하기 위한 객체
	private Graphics bg;
	private Image offSchreen;//백그라운드 이미지 객체
	private Dimension dim;//화면의 가로 세로 크기 계산
	//애플릿을 초기화 하는 코드
	public void init() {
		setSize(300,300);//화면의 가로 세로의 사이즈 설정
		dim=getSize();//화면 사이즈 저장
		//백그라운드 이미지 생성, createImage(가로, 세로)
		offSchreen=createImage(dim.width, dim.height);
		//백그라운드 이미치처리를 위한 객체 생성
		bg=offSchreen.getGraphics();
		Thread t=new Thread(this);
		t.start();//백그라운드 쓰레드 시작 요청
	}
	@Override
	public void run() {
		while(true) {
			if(x > (dim.width - 30) || x<0) { //좌우 벽처리
				moveX = -moveX;//x축 이동방향 바꾸기
			}
			x = x + moveX;//x축 좌표값 바꾸기
			if(y > (dim.height - 30) || y<0) { //상하 벽처리
				moveY = -moveY;//y축 이동방향 바꾸기
			}
			y = y + moveY;//y축 좌표값 바꾸기
			repaint();//paint()호출 그래픽 화면 갱신 요청
			try {
				Thread.sleep(30);//잠시 멈춤
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void paint(Graphics g) {
		//백그라운드 이미지에 그래픽 처리
		bg.setColor(Color.yellow);//색상설정
		//사각형 그리기 fillrect(x1,y1,x2,y2)
		bg.fillRect(0, 0, getWidth(), getHeight());
		bg.setColor(Color.green);
		//타원그리기 (x,y,width,heigth)
		bg.fillOval(x, y, 30, 30);
		bg.setColor(Color.blue);
		//문자열 그리기(문자열,x좌표,y좌표)
		bg.drawImage(offSchreen, 0, 0, this);
	}
	
	
}
