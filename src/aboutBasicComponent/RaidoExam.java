package aboutBasicComponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class RaidoExam extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RaidoExam frame = new RaidoExam();
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
	public RaidoExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JRadioButton red = new JRadioButton("red");
		red.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				contentPane.setBackground(Color.red);
			}
		});
		buttonGroup.add(red);
		contentPane.add(red);
		
		JRadioButton green = new JRadioButton("green");
		green.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				contentPane.setBackground(Color.green);
			}
		});
		buttonGroup.add(green);
		contentPane.add(green);
		
		JRadioButton blue = new JRadioButton("blue");
		blue.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				contentPane.setBackground(Color.blue);
			}
		});
		buttonGroup.add(blue);
		contentPane.add(blue);
		
		JRadioButton yellow = new JRadioButton("yellow");
		yellow.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				contentPane.setBackground(Color.yellow);
			}
		});
		buttonGroup.add(yellow);
		contentPane.add(yellow);
		
		JRadioButton chan = new JRadioButton("cyan");
		chan.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				contentPane.setBackground(Color.cyan);
			}
		});
		buttonGroup.add(chan);
		contentPane.add(chan);
	}

}
