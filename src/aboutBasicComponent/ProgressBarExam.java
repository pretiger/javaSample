package aboutBasicComponent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProgressBarExam extends JFrame {

	private JPanel contentPane;
	private JButton btnStart;
	private JProgressBar progress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgressBarExam frame = new ProgressBarExam();
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
	public ProgressBarExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		progress = new JProgressBar();
		progress.setStringPainted(true);
		progress.setBounds(31, 34, 363, 31);
		contentPane.add(progress);
		
		btnStart = new JButton("시작");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread t=new Thread(new Runnable() {
					
					@Override
					public void run() {
						for(int i=0;i<100;i++) {
							progress.setValue(i);
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						JOptionPane.showMessageDialog(ProgressBarExam.this, "작업완료");
					}
				});
				t.start();
			}
		});
		btnStart.setBounds(31, 98, 97, 23);
		contentPane.add(btnStart);
	}

}
