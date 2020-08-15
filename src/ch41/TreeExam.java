package ch41;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeExam extends JFrame {

	private JPanel contentPane;
	//코드추가
	private Object[] obj= {"프로그램", "시스템", "디자인"};
	//벡터선언
	private Vector<Vector> node1=new Vector<>() {
		@Override
		public String toString() {
			return "Lesson";
		}
	};
	private Vector<String> node2=new Vector<>() {
		@Override
		public String toString() {
			return "Java";
		}
	};
	private Vector<String> node3=new Vector<>() {
		@Override
		public String toString() {
			return "XML";
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
		
		//트리에 입력될 데이터 구성
		node1.add(node2); //node1(루트노드)에 node2를 붙임
		node1.add(node3);
		node2.add("C++");//node2에 아이템 추가
		node2.add("Java");
		node2.add("Jsp");
		node3.add("XSLT");
		node3.add("DOM");
		obj[0]=node1;//node1을 첫번째 node로 설정

		JTree tree = new JTree(obj);
//		tree.setModel(new DefaultTreeModel(
//			new DefaultMutableTreeNode("JTree") {
//				{
//					DefaultMutableTreeNode node_1;
//					node_1 = new DefaultMutableTreeNode("colors");
//						node_1.add(new DefaultMutableTreeNode("blue"));
//						node_1.add(new DefaultMutableTreeNode("violet"));
//						node_1.add(new DefaultMutableTreeNode("red"));
//						node_1.add(new DefaultMutableTreeNode("yellow"));
//					add(node_1);
//					node_1 = new DefaultMutableTreeNode("sports");
//						node_1.add(new DefaultMutableTreeNode("basketball"));
//						node_1.add(new DefaultMutableTreeNode("soccer"));
//						node_1.add(new DefaultMutableTreeNode("football"));
//						node_1.add(new DefaultMutableTreeNode("hockey"));
//					add(node_1);
//					node_1 = new DefaultMutableTreeNode("food");
//						node_1.add(new DefaultMutableTreeNode("hot dogs"));
//						node_1.add(new DefaultMutableTreeNode("pizza"));
//						node_1.add(new DefaultMutableTreeNode("ravioli"));
//						node_1.add(new DefaultMutableTreeNode("bananas"));
//					add(node_1);
//					node_1 = new DefaultMutableTreeNode("과일");
//						node_1.add(new DefaultMutableTreeNode("배"));
//						node_1.add(new DefaultMutableTreeNode("사과"));
//						node_1.add(new DefaultMutableTreeNode("딸기"));
//					add(node_1);
//				}
//			}
//		));
		scrollPane.setViewportView(tree);
	}

}
