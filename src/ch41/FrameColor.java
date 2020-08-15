package ch41;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

public class FrameColor extends JFrame {
	public FrameColor() throws InterruptedException {
		setVisible(true);//프레임을 화면에 표시
		Container c=getContentPane();//프레임의 컨텐츠 영역
		for(int i=0; i<255; i++) {
			setSize(i*2, i);//프레임의 크기 설정
			setLocation(i*2, i);//프레임이 출력될 좌표설정
			c.setBackground(new Color(i,0,0));//배경 색상 설정
			Thread.sleep(10);
		}
		for(int i=0; i<255; i++) {
			setSize(i*2, i);
			setLocation(i*2, i);
			c.setBackground(new Color(255,i,0));
			Thread.sleep(10);
		}
	}
	public static void main(String[] args) throws InterruptedException {
		new FrameColor();
	}
}
