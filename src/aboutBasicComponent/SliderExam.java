package aboutBasicComponent;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalSliderUI;

public class SliderExam extends JFrame {

	private JPanel contentPane;
	private JSlider sliderB;
	private JSlider sliderG;
	private JSlider sliderR;
	private JPanel panel;
	private int red,green,blue;

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
		setBounds(100, 100, 376, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		sliderR = new JSlider();
		sliderR.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				red=sliderR.getValue();
				panel.setBackground(new Color(red,green,blue));
			}
		});
		sliderR.setUI(new MetalSliderUI() {protected void scrollDueToClickInTrack(int dir) {
			red=valueForXPosition(sliderR.getMousePosition().x);
			sliderR.setValue(red);
			panel.setBackground(new Color(red,green,blue));
			}
		});
		sliderR.setValue(0);
		sliderR.setPaintLabels(true);//슬라이드상에 수치 표시
		sliderR.setPaintTicks(true);//이부분이 true가 되있어야 아래 눈끔이 표시 된다.
		sliderR.setMajorTickSpacing(40);//큰눈끔
		sliderR.setMinorTickSpacing(10);//작은 눈끔
		sliderR.setMaximum(255);
		sliderR.setBounds(12, 10, 200, 60);
		contentPane.add(sliderR);
		
		sliderG = new JSlider();
		sliderG.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				green=sliderG.getValue();
				panel.setBackground(new Color(red,green,blue));
			}
		});
		sliderG.setUI(new MetalSliderUI() {  //슬라이드위에서 마우스 클릭시 이벤트 처리
			protected void  scrollDueToClickInTrack( int dir ) {
				green=valueForXPosition(sliderG.getMousePosition().x);
				sliderG.setValue(green);
				panel.setBackground(new Color(red,green,blue));
			}
		});
		sliderG.setPaintLabels(true);
		sliderG.setPaintTicks(true);
		sliderG.setMajorTickSpacing(40);
		sliderG.setMinorTickSpacing(10);
		sliderG.setValue(0);
		sliderG.setMaximum(255);
		sliderG.setBounds(12, 79, 200, 44);
		contentPane.add(sliderG);
		
		sliderB = new JSlider();
		sliderB.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				blue=sliderB.getValue();
				panel.setBackground(new Color(red,green,blue));
			}
		});
		sliderB.setUI(new MetalSliderUI() {
			protected void scrollDueToClickInTrack(int dir) {
				blue=valueForXPosition(sliderB.getMousePosition().x);
				sliderB.setValue(blue);
				panel.setBackground(new Color(red,green,blue));
			}
		});
		sliderB.setValue(0);
		sliderB.setPaintLabels(true);
		sliderB.setPaintTicks(true);
		sliderB.setMajorTickSpacing(40);
		sliderB.setMinorTickSpacing(10);
		sliderB.setMaximum(255);
		sliderB.setBounds(12, 122, 200, 60);
		contentPane.add(sliderB);
		
		
		panel.setBounds(12, 223, 336, 191);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("빨간색");
		lblNewLabel.setBounds(259, 29, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("초록색");
		label.setBounds(259, 93, 57, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("파란색");
		label_1.setBounds(259, 145, 57, 15);
		contentPane.add(label_1);
	}
}
