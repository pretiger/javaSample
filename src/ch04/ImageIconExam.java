package ch04;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ImageIconExam extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JLabel lblImage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageIconExam frame = new ImageIconExam();
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
	public ImageIconExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		btnNewButton = new JButton("파일선택");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc=new JFileChooser();
				int result=fc.showOpenDialog(ImageIconExam.this);
				if(result==JFileChooser.APPROVE_OPTION) {
					File file=fc.getSelectedFile();
					String img_path=fc.getSelectedFile().getAbsolutePath();
					try {
						ImageIcon icon=new ImageIcon(ImageIO.read(file));
						Image imageSource=icon.getImage();
						BufferedImage bi=ImageIO.read(file);
						File copyFile=new File("test.jpg");
						ImageIO.write(bi,"jpg",copyFile);
						Image newImage=imageSource.getScaledInstance(140, 100, Image.SCALE_AREA_AVERAGING);
						icon=new ImageIcon(newImage);
						lblImage.setIcon(icon);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		
		lblImage = new JLabel("");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblImage, BorderLayout.CENTER);
	}

}
