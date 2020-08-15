package aboutGraphics;

import java.applet.AudioClip;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
//AudioClip 자체가 version9 이후부터 지원이 되지 않아 여기서 중단
public class SoundExam extends JFrame implements ActionListener {
	AudioClip audio;
	JButton play, stop, loop;
	
	public SoundExam() {
		setSize(300,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new FlowLayout());
		
		JFileChooser fc=new JFileChooser();
		int result=fc.showOpenDialog(SoundExam.this);
		if(result==JFileChooser.APPROVE_OPTION) {
			File file=fc.getSelectedFile();
//			audio=
		}
	}
	public static void main(String[] args) {
		new SoundExam();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
