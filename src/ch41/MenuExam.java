package ch41;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuExam extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuExam frame = new MenuExam();
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
	public MenuExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("파일");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("새파일");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("열기");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("저장");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem miExit = new JMenuItem("종료");
		miExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result=JOptionPane.showConfirmDialog(MenuExam.this, "종료할까요?");
				if(result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		mnNewMenu.add(miExit);
		
		JMenu mnNewMenu_1 = new JMenu("색상");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem miRed = new JMenuItem("Red");
		miRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//getcontentPane() 프레임의 컨텐츠 영역을 리턴
				//배경색상을 변경
				getContentPane().setBackground(Color.red);
			}
		});
		mnNewMenu_1.add(miRed);
		
		JMenuItem miGreen = new JMenuItem("Green");
		miGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//색상 다이얼로그에서 선택한 값이 저장
				Color color=JColorChooser.showDialog(MenuExam.this, "색상을 선택하세요", Color.blue);
				//배경 색상 변경
				contentPane.setBackground(color);
//				getContentPane().setBackground(Color.green);
			}
		});
		mnNewMenu_1.add(miGreen);
		
		JMenuItem miBlue = new JMenuItem("Blue");
		miBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setBackground(Color.blue);
			}
		});
		mnNewMenu_1.add(miBlue);
		
		JMenu mnNewMenu_2 = new JMenu("도움말");
		menuBar.add(mnNewMenu_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
