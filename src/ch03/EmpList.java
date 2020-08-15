package ch03;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class EmpList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox comboBox;
	private ArrayList<DeptDTO> deptList;
	private Vector data, col;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpList frame = new EmpList();
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
	public EmpList() {
		DeptDAO dao=new DeptDAO();
		table = new JTable();
		
		col=new Vector();
		col.add("사원번호");
		col.add("이름");
		col.add("직위");
		col.add("입사일자");
		col.add("급여");
		col.add("부서명");
		
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
					String dname=comboBox.getSelectedItem().toString();
					System.out.println(comboBox.getSelectedItem());
					if(dname.equals("전체부서")) dname="%";
					refreshTable(dname);
				}
			}
		});
		deptList=dao.listDept();
		comboBox.addItem("전체부서");
		for(int i=0; i<deptList.size(); i++) {
			comboBox.addItem(deptList.get(i).getDname());
		}
		contentPane.add(comboBox, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.setViewportView(table);
		refreshTable("%");
	}
	
	void refreshTable(String dname) {
		EmpDAO empDao=new EmpDAO();
		DefaultTableModel model=new DefaultTableModel(empDao.listAllEmp(dname), col);
		table.setModel(model);
	}
}
