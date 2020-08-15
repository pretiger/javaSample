package aboutTCP;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class GuguClient extends JFrame {

	private JPanel contentPane;
	private JTextArea ta;
	private JComboBox cboDan;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuguClient frame = new GuguClient();
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
	public GuguClient() {
		
		try {
			socket=new Socket("localhost", 9100);
			dis=new DataInputStream(socket.getInputStream());
			dos=new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("단을 선택하세요");
		panel.add(lblNewLabel);
		
		cboDan = new JComboBox();
		cboDan.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
				int dan=Integer.parseInt(cboDan.getSelectedItem().toString());
					try {
					dos.writeInt(dan);
					ta.setText(dis.readUTF());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		cboDan.setModel(new DefaultComboBoxModel(new String[] {"2", "3", "4", "5", "6", "7", "8", "9"}));
		panel.add(cboDan);
		
		ta = new JTextArea();
		contentPane.add(ta, BorderLayout.CENTER);
	}

}
