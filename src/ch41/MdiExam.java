package ch41;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MdiExam extends JFrame implements Runnable {

	private JPanel contentPane;
	private JProgressBar progress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MdiExam frame = new MdiExam();
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
	public MdiExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setClosable(true);
		internalFrame.setBounds(52, 20, 321, 212);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		progress = new JProgressBar();
		progress.setBounds(12, 29, 146, 23);
		internalFrame.getContentPane().add(progress);
		
		JButton btnStart = new JButton("시작");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread th=new Thread(MdiExam.this);
				th.start();
			}
		});
		btnStart.setBounds(170, 29, 97, 23);
		internalFrame.getContentPane().add(btnStart);
		internalFrame.setVisible(true);
	}

	@Override
	public void run() {
		for(int i=0; i<100; i++) {
		progress.setValue(i);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(this, "완료되었습니다.");
	}
}
