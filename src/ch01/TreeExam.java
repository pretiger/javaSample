package ch01;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTree;

public class TreeExam extends JFrame {

	private JPanel contentPane;
	private Object[] obj= {"Lesson"};

	private Vector<Vector> node1=new Vector<>() {
		@Override
		public String toString() {
			return "Lesson";
		}
	};
	private Vector<String> node2=new Vector<>() {
		@Override
		public String toString() {
			return "Program Language";
		}
	};
	private Vector<String> node3=new Vector<>() {
		@Override
		public String toString() {
			return "HTML";
		}
	};
	
	private Vector<String> node4=new Vector<>() {
		@Override
		public String toString() {
			return "DB";
		}
	};
	private Vector<String> node5=new Vector<>() {
		@Override
		public String toString() {
			return "Vendor";
		}
	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TreeExam frame = new TreeExam();
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
	public TreeExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		node1.add(node2);
		node1.add(node3);
		node1.add(node4);
		node1.add(node5);
		node2.add("C++");
		node2.add("Java");
		node2.add("jsp");
		node3.add("XSLT");
		node3.add("DOM");
		node4.add("Oracle");
		node4.add("DB2");
		node4.add("Infomix");
		node5.add("Sun");
		node5.add("HP");
		node5.add("IBM");
		
		obj[0]=node1;
		
		JTree tree = new JTree(obj);
		scrollPane.setViewportView(tree);
	}

}
