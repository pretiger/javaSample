package ch03;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ComboExam extends JFrame {

	private JPanel contentPane;
	private JList list;
	private JComboBox comboBox;
	private ArrayList<DeptDTO> deptList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComboExam frame = new ComboExam();
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
	public ComboExam() {
		DeptDAO dao=new DeptDAO();
		deptList=new ArrayList<>();
		deptList=dao.listDept();
		list = new JList();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					int idx=comboBox.getSelectedIndex();
					refreshList(deptList.get(idx).getDeptno());
				}
			}
		});
		for(int i=0; i<deptList.size(); i++) {
			comboBox.addItem(deptList.get(i).getDname()+":"+deptList.get(i).getDeptno());
		}
		contentPane.add(comboBox, BorderLayout.NORTH);
		
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
		refreshList(deptList.get(0).getDeptno());
		scrollPane.setViewportView(list);
	}
	void refreshList(int idx) {
		list.removeAll();
		EmpDAO dao=new EmpDAO();
		ArrayList<EmpDTO> empList=new ArrayList<>();
		empList=dao.listEmp(idx);
		DefaultListModel<String> model=new DefaultListModel<>();
		for(int i=0; i<empList.size(); i++) {
			model.addElement(empList.get(i).getEname());
		}
		list.setModel(model);
	}
}
