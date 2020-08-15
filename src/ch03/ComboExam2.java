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

public class ComboExam2 extends JFrame {

	private JPanel contentPane;
	private JList list;
	private JComboBox comboBox;
	private DeptDAO deptDao;
	private EmpDAO empDao;
	private ArrayList<DeptDTO> deptList;
	private ArrayList<EmpDTO> empList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComboExam2 frame = new ComboExam2();
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
	public ComboExam2() {
		deptDao=new DeptDAO();
		empDao=new EmpDAO();
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
					refreshList(idx);
				}
			}
		});
		contentPane.add(comboBox, BorderLayout.NORTH);
		
		deptList=deptDao.listDept();
		for(DeptDTO dto : deptList) {
			comboBox.addItem(dto.getDname());
		}
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		empList=empDao.listEmp(10);
		

		refreshList(0);
		scrollPane.setViewportView(list);
	}
	
	void refreshList(int idx) {
		list.removeAll();
		int deptno=deptList.get(idx).getDeptno();
		empDao=new EmpDAO();
		empList=empDao.listEmp(deptno);
		String[] names=new String[empList.size()];
		for(int i=0; i<names.length; i++) {
			names[i]=empList.get(i).getEname();
		}
//		System.out.println(empList);
		DefaultListModel<String> model=new DefaultListModel<>();
		for(String val : names) {
			model.addElement(val);
		}
		list.setModel(model);
	}
}
