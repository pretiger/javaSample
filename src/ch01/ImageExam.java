package ch01;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ImageExam extends JFrame implements ActionListener{
	Image curImage;
	Panel p,view;
	JButton btnNormal, btnPart, btnScale, btnHori, btnVert, btnAll;
	Dimension d;
	int command;
	static final int SHOW_NORM=0;
	static final int SHOW_PART=1;
	static final int SHOW_SCALE=2;
	static final int SHOW_HORI=3;
	static final int SHOW_VERT=4;
	static final int SHOW_ALL=5;
	
	public ImageExam() {
		curImage=Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("flower.jpg"));
		p=new Panel();
		btnNormal=new JButton("정상그림");
		p.add(btnNormal);
		p.add(btnPart=new JButton("그림일부"));
		p.add(btnScale=new JButton("축소"));
		p.add(btnHori=new JButton("좌우뒤집기"));
		p.add(btnVert=new JButton("상하뒤집기"));
		p.add(btnAll=new JButton("전부뒤집기"));
		add(p, "North");
		btnNormal.addActionListener(this);
		btnPart.addActionListener(this);
		btnScale.addActionListener(this);
		btnHori.addActionListener(this);;
		btnVert.addActionListener(this);
		btnAll.addActionListener(this);
		setSize(600,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn=(JButton)e.getSource();
		if(btn==btnNormal)		command=SHOW_NORM;
		else if(btn==btnPart)	command=SHOW_PART;
		else if(btn==btnScale)	command=SHOW_SCALE;
		else if(btn==btnHori)	command=SHOW_HORI;
		else if(btn==btnVert)	command=SHOW_VERT;
		else if(btn==btnAll)	command=SHOW_ALL;
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		d=p.getSize();
		switch(command) {
		case SHOW_NORM:
			g.drawImage(curImage, 0, 0, this); break;
		
		}
	}
	
	public static void main(String[] args) {
		new ImageExam();
	}

}
