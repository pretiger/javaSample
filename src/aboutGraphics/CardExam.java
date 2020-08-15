package aboutGraphics;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CardExam extends JFrame {
	public CardExam() {
		Container con=getContentPane();
		CardLayout card=new CardLayout();
		setLayout(card);
		setSize(300,300);
		JPanel[] pan=new JPanel[5];
		Color[] color= {Color.red, Color.yellow, Color.green, Color.cyan, Color.blue};
		for(int i=0;i<5;i++) {
			pan[i]=new JPanel();
			pan[i].setBackground(color[i]);
			add(pan[i]);
			pan[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					card.next(con);
				}
			});
		}
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new CardExam();
	}
}
