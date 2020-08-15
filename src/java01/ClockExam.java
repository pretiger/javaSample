package java01;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClockExam extends JFrame implements Runnable {

	private JPanel contentPane;
	
	private Calendar cal;
	private String str;
	private JLabel lblTime;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnClose = new JButton("프로그램 종료");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(btnClose, BorderLayout.NORTH);
		
		lblTime = new JLabel("New Label");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(lblTime, BorderLayout.CENTER);
		//백그라운드 스레드 생성
		Thread t=new Thread(this);
		//백그라운드 스레드 실행 요청
		t.start(); //run()이 실행됨
	}

	@Override
	public void run() {
		while(true) {//무한반복
			//캘린더 인스턴스 생성(현재 시간 정보가 cal 변수에 저장)
			cal=Calendar.getInstance();
			//02d 숫자 2자리 , 빈자리는 0으로 채움
			str=String.format("%04d년 %02d월 %2d일 %02d:%02d:%02d",
					cal.get(Calendar.YEAR),
					cal.get(Calendar.MONTH)+1,
					cal.get(Calendar.DAY_OF_MONTH),
					cal.get(Calendar.HOUR_OF_DAY),
					cal.get(Calendar.MINUTE),
					cal.get(Calendar.SECOND));
			lblTime.setText(str);
			//레이블에 시간 출력
			try {
				Thread.sleep(1000);//1초간 멈춤
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
