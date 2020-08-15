package aboutBasicComponent;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ClockExam extends JFrame implements Runnable {

	private JPanel contentPane;
	private JLabel lbl;
	private Calendar cal;
	private String str;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClockExam frame = new ClockExam();
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
	public ClockExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnExit = new JButton("종료");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(btnExit, BorderLayout.NORTH);
		
		lbl = new JLabel("New label");
		lbl.setFont(new Font("굴림", Font.PLAIN, 40));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbl, BorderLayout.CENTER);
		Thread th=new Thread(this);
		th.start();
	}

	@Override
	public void run() {
		while(true) {
			cal=Calendar.getInstance();
			str=String.format("%d년 %d월 %d일   %d:%d:%d",	cal.get(Calendar.YEAR),
					cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH),
					cal.get(Calendar.HOUR_OF_DAY),
					cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
			lbl.setText(str);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
