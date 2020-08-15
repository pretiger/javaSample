package ch41;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalSliderUI;
import javax.swing.event.ChangeEvent;

public class SliderExam extends JFrame {

	private JPanel contentPane;
	private JSlider sliderR;
	private JSlider sliderG;
	private JSlider sliderB;
	private JPanel panel;
	private int red, green, blue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SliderExam frame = new SliderExam();
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
	public SliderExam() {
		panel = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		sliderR = new JSlider();
		sliderR.setPaintLabels(true);
		sliderR.setPaintTicks(true);
		sliderR.setMajorTickSpacing(40);
		sliderR.setMinorTickSpacing(20);
		sliderR.addChangeListener(new ChangeListener() {
			//슬라이더의 값이 변경될 때 실행되는 코드
			public void stateChanged(ChangeEvent e) {
				//슬라이더의 값을 저장
				red=sliderR.getValue();
				//패널의 배경색상 변경
				panel.setBackground(new Color(red,green,blue));
			}
		});
		//슬라이더에 마우스 클릭 이동 기능 추가
		sliderR.setUI(new MetalSliderUI() {
			//마우스 클릭한 위치의 슬라이더
			protected void scrollDueToClickInTrack(int direction) {
				red=valueForXPosition(sliderR.getMousePosition().x);
				sliderR.setValue(red);//슬라이더의 값 변경
				//패널의 배경색상변경
				panel.setBackground(new Color(red,green,blue));
			}
		});
		
		
		sliderR.setValue(0);
		sliderR.setMaximum(255);
		sliderR.setBounds(152, 22, 270, 46);
		contentPane.add(sliderR);
		
		sliderG = new JSlider();
		sliderG.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//슬라이더의 값을 저장
				green=sliderG.getValue();
				//패널의 배경색상 변경
				panel.setBackground(new Color(red,green,blue));
			}
		});
		//슬라이더에 마우스 클릭 이동 기능 추가
		sliderG.setUI(new MetalSliderUI() {
			//마우스 클릭한 위치의 슬라이더
			protected void scrollDueToClickInTrack(int direction) {
				green=valueForXPosition(sliderG.getMousePosition().x);
				sliderG.setValue(green);//슬라이더의 값 변경
				//패널의 배경색상변경
				panel.setBackground(new Color(red,green,blue));
			}
		});
		sliderG.setPaintLabels(true);
		sliderG.setPaintTicks(true);
		sliderG.setMajorTickSpacing(40);
		sliderG.setMinorTickSpacing(20);
		sliderG.setMaximum(255);
		sliderG.setValue(0);
		sliderG.setBounds(152, 105, 270, 38);
		contentPane.add(sliderG);
		
		sliderB = new JSlider();
		sliderB.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//슬라이더의 값을 저장
				blue=sliderB.getValue();
				//패널의 배경색상 변경
				panel.setBackground(new Color(red,green,blue));
			}
		});
		//슬라이더에 마우스 클릭 이동 기능 추가
		sliderB.setUI(new MetalSliderUI() {
			//마우스 클릭한 위치의 슬라이더
			protected void scrollDueToClickInTrack(int direction) {
				blue=valueForXPosition(sliderB.getMousePosition().x);
				sliderB.setValue(blue);//슬라이더의 값 변경
				//패널의 배경색상변경
				panel.setBackground(new Color(red,green,blue));
			}
		});
		sliderB.setPaintLabels(true);
		sliderB.setPaintTicks(true);
		sliderB.setMajorTickSpacing(40);
		sliderB.setMinorTickSpacing(20);
		sliderB.setValue(0);
		sliderB.setMaximum(255);
		sliderB.setBounds(152, 178, 270, 46);
		contentPane.add(sliderB);
		
		JLabel lblNewLabel = new JLabel("Red");
		lblNewLabel.setBounds(25, 22, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Green");
		lblNewLabel_1.setBounds(25, 105, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Blue");
		lblNewLabel_2.setBounds(25, 178, 57, 15);
		contentPane.add(lblNewLabel_2);
		
		
		panel.setBounds(25, 249, 397, 224);
		contentPane.add(panel);
	}
}
